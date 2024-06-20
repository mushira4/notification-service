package com.mybank.input.console;

import com.mybank.core.domain.NotificationType;
import com.mybank.core.service.NotificationService;

import java.util.Scanner;

public class NotificationInputConsole {
    private final NotificationService service;

    public NotificationInputConsole(NotificationService service) {
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
