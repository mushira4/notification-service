package com.mybank.notification.core.service;

import com.mybank.notification.core.domain.Notification;
import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.infra.gateway.Gateway;
import com.mybank.notification.core.repository.RateLimitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private RateLimitRepository mockedRepository;

    @Mock
    private Gateway mockedGateway;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void testSendNotification() {
        NotificationType type = NotificationType.STATUS;
        Notification notification = new Notification(type, "user1", "message1");

        when(mockedRepository.findNumberOfEntries(notification)).thenReturn(1);

        notificationService.send(type, "user1", "message1");

        verify(mockedRepository, times(1)).save(notification);
        verify(mockedGateway, times(1)).send(any());
    }

    @Test
    void testDoNotSendNotification() {
        NotificationType type = NotificationType.STATUS;
        Notification notification = new Notification(type, "user1", "message1");

        when(mockedRepository.findNumberOfEntries(notification)).thenReturn(10);

        notificationService.send(type, "user1", "message1");

        verify(mockedRepository, times(0)).save(notification);
        verify(mockedGateway, times(0)).send(any());
    }

}
