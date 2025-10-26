package com.bankingsystem.banking_system.controller;

import com.bankingsystem.banking_system.dto.notification.NotificationDTO;
import com.bankingsystem.banking_system.service.impl.NotificationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServiceImpl notificationService;

    @Operation(
            summary = "Create a notification",
            description = "Creates a new notification for a user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Notification created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NotificationDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"userId\": 123, \"title\": \"Payment Received\", \"message\": \"You received $100\", \"read\": false }")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid notification data")
            }
    )
    @PostMapping
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(notificationService.createNotification(dto));
    }

    @Operation(
            summary = "Get user notifications",
            description = "Retrieves all notifications for a specific user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of notifications",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NotificationDTO.class),
                                    examples = @ExampleObject(value = "[{ \"id\": 1, \"userId\": 123, \"title\": \"Payment Received\", \"message\": \"You received $100\", \"read\": false }]")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getAllNotificationsForUser(userId));
    }

    @Operation(
            summary = "Mark notification as read",
            description = "Marks a specific notification as read.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Notification marked as read",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NotificationDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"userId\": 123, \"title\": \"Payment Received\", \"message\": \"You received $100\", \"read\": true }")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Notification not found")
            }
    )
    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationDTO> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }
}
