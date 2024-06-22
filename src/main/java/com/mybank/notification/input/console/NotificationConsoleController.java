package com.mybank.notification.input.console;

import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.core.service.NotificationService;

import java.util.Scanner;

public class NotificationConsoleController {
    private final NotificationService service;

    public NotificationConsoleController(NotificationService service) {
        this.service = service;
    }

    public void initConsole(){
        while (true) {
            printInputMessage();

            String entry = captureEntry();
            String[] entriesSplit = entry.split(",");

            NotificationType notificationType = NotificationType.getNotificationType(entriesSplit[0]);
            service.send(notificationType, entriesSplit[1], entriesSplit[2]);
        }
    }

    public void printInputMessage(){
        System.out.println("Enter type, user and message separated by commas:");
    }

    public String captureEntry(){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        return scanner.nextLine();
    }
}
