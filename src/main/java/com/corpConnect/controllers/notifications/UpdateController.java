package com.corpConnect.controllers.notifications;

import com.corpConnect.configs.notification.WebSocketHandler;
import com.corpConnect.dtos.notifications.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateController {

    private final WebSocketHandler webSocketHandler;

    @Autowired
    public UpdateController(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @PostMapping("/update-socket")
    public ResponseEntity<String> sendUpdate(@RequestBody UpdateRequest updateRequest) {
        String message = updateRequest.getCount() + " Data has been updated!";
        webSocketHandler.broadcast("{\"message\": \"" + message + "\"}");
        webSocketHandler.sendMessageToUsers(new ArrayList<>(List.of("fenil@gmail.com", "rohan@gmail.com")), "This message is only for you");
        return ResponseEntity.ok("Update notification sent to all clients.");
    }
}
