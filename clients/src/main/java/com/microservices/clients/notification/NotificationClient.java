package com.microservices.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification",
        url = "${notification.fraud.url}"
)
public interface NotificationClient {

    @PostMapping(path = "api/v1/notification")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
