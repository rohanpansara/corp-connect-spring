package com.corpConnect.controllers.notifications;

import com.corpConnect.configs.notification.CustomWebSocketHandler;
import com.corpConnect.dtos.notifications.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateController {

    private final CustomWebSocketHandler customWebSocketHandler;

    @Autowired
    public UpdateController(CustomWebSocketHandler customWebSocketHandler) {
        this.customWebSocketHandler = customWebSocketHandler;
    }

    @PostMapping("/update-socket")
    public ResponseEntity<String> sendUpdate(@RequestBody UpdateRequest updateRequest) {
        String message = updateRequest.getCount() + " Data has been updated!";
        customWebSocketHandler.broadcast("{\"message\": \"" + message + "\"}");
        customWebSocketHandler.sendMessageToUsers(new ArrayList<>(List.of("fenil@gmail.com", "rohan@gmail.com")), "This message is only for you");
        return ResponseEntity.ok("Update notification sent to all clients.");
    }
}
