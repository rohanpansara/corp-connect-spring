package com.corpConnect.configs.notification;

import com.corpConnect.security.services.JwtService;
import com.corpConnect.utils.constants.CookieConstants;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CustomWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
    private final Map<String, Set<String>> sessionsForUser = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, WebSocketSession> sessionsForSessionId = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String token = null;

        // Extract token from cookies
        List<String> cookies = session.getHandshakeHeaders().get("cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                String[] cookiePairs = cookie.split(";");
                for (String pair : cookiePairs) {
                    String[] keyValue = pair.trim().split("=");
                    if (keyValue.length == 2 && CookieConstants.TOKEN_COOKIE_NAME.equals(keyValue[0])) { // Assuming your cookie name is "token"
                        token = keyValue[1];
                        break;
                    }
                }
            }
        }

        if (token == null || token.isEmpty() || !isValidToken(token)) {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Invalid or missing token"));
            return;
        }

        String email = jwtService.extractEmailFromToken(token);
        sessions.add(session);
        if (sessionsForUser.containsKey(email)) {
            synchronized (sessionsForUser) {
                Set<String> existingSet = sessionsForUser.get(email);
                existingSet.add(session.getId());
                sessionsForUser.put(email, existingSet);
            }
        } else {
            synchronized (sessionsForUser) {
                Set<String> newSet = Collections.synchronizedSet(new HashSet<>());
                newSet.add(session.getId());
                sessionsForUser.put(email, newSet);
            }
        }
        sessionsForSessionId.put(session.getId(), session);
        session.sendMessage(new TextMessage("{\"message\": \"Welcome to the WebSocket server!\"}"));
        System.out.println("Client: " + email + " connected with session id: " + session.getId());
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        sessions.remove(session);

        sessionsForUser.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(session.getId()))
                .map(Map.Entry::getKey)
                .findFirst().ifPresent(sessionsForUser::remove);

        sessionsForSessionId.remove(session.getId());

        System.out.println("Client disconnected: " + session.getId());
    }

    public void broadcast(String message) {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(message));
                    }
                } catch (IOException e) {
                    System.err.println("Failed to send broadcast message to session: " + session.getId());
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessageToUsers(List<String> userEmails, String message) {
        for (String userEmail : userEmails) {
            Set<String> sessionIds = sessionsForUser.containsKey(userEmail) ? sessionsForUser.get(userEmail) : Collections.synchronizedSet(new HashSet<>());
            synchronized (sessionsForUser) {
                for (String sessionId : sessionIds) {
                    WebSocketSession session = sessionsForSessionId.get(sessionId);

                    if (session != null && session.isOpen()) {
                        try {
                            session.sendMessage(new TextMessage("{\"message\":\"" + message + "\",\"user\":\"" + userEmail + "\"}"));
                        } catch (IOException e) {
                            System.err.println("Failed to send message to user: " + userEmail);
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("No active session for user: " + userEmail);
                    }
                }
            }
        }
    }

    private boolean isValidToken(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractEmailFromToken(token));
        return jwtService.isTokenValid(token, userDetails); // Implement actual validation logic
    }
}

