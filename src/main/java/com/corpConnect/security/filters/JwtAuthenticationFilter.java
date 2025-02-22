package com.corpConnect.security.filters;

import com.corpConnect.entities.user.User;
import com.corpConnect.exceptions.jwt.JwtAuthenticationException;
import com.corpConnect.security.CorpConnectUserContext;
import com.corpConnect.security.services.JwtService;
import com.corpConnect.utils.constants.CookieConstants;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.functions.CookieUtils;
import com.corpConnect.utils.functions.CustomDateTimeFormatter;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final String[] PUBLIC_URLS = {
            "/auth/login",
            "/auth/logout",
            "/auth/validate-token",
            "/public/new-user",
            "/public/verify-otp",
            "/public/set-password",
            "/ws/**"
    };


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final CookieUtils cookieUtils;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {

            logger.info("Request remote address: {} | DateTime: {}", request.getRemoteAddr(), CustomDateTimeFormatter.getLocalDateTimeString(LocalDateTime.now()));

            // Check if the request is for a public URL
            String servletPath = request.getServletPath();
            for (String url : PUBLIC_URLS) {
                if (servletPath.equals(url)) {
                    logger.info("Public URL '{}' accessed by: {}", servletPath, request.getRemoteAddr());
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            // Extract token from the cookie
            String jwtToken = cookieUtils.getCookieValueByName(request, CookieConstants.TOKEN_COOKIE_NAME);
            final String userEmail;
            if (jwtToken == null || jwtToken.isEmpty()) {
                logger.error("JWT: Authorization token missing for request: {}", servletPath);
                filterChain.doFilter(request, response);
                throw new JwtAuthenticationException("Authorization token missing");
            }
            logger.info("JWT: Token found with value - {}", jwtToken);

            // Extract email from the token
            userEmail = jwtService.extractEmailFromToken(jwtToken);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.info("JWT: Extracted user email - {}", userEmail);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwtToken, userDetails)) {
                    logger.info("JWT: Token is valid, setting authentication for user: {}", userEmail);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    CorpConnectUserContext.setCurrentUser((User) userDetails);
                } else {
                    logger.error("JWT: Token is invalid for user: {}", userEmail);
                }
            } else {
                logger.error("JWT: User email is null or authentication already set.");
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            logger.error("JWT: Token is expired, error details: {}", e.getMessage());
            throw new JwtAuthenticationException(MessageConstants.JWT.EXPIRED_JWT_EXCEPTION, "403");
        } catch (JwtAuthenticationException e) {
            logger.error("JWT: Token authentication failed, error details: {}", e.getMessage());
            throw new JwtAuthenticationException(MessageConstants.UserError.USER_NOT_LOGGED_IN, "401");
        } catch (Exception e) {
            logger.error("Authorization failed, error details: {}", e.getMessage());
            throw new RuntimeException(MessageConstants.UserError.AUTHORIZATION_FAILED);
        } finally {
            logger.info("Clearing CorpConnectUserContext");
            CorpConnectUserContext.clear();
        }
    }
}
