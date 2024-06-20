package com.mybank.core.repository;

import com.mybank.core.domain.Notification;
import com.mybank.core.domain.NotificationType;
import com.mybank.core.repository.RateLimitRepository;
import com.mybank.infra.cache.Cache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateLimitRepositoryTest {

    @Mock
    private Cache mockedCache;

    @InjectMocks
    private RateLimitRepository rateLimitRepository;

    @Test
    void testSaveNotification() {
        Notification notification = new Notification(NotificationType.STATUS, "user1", "message1");

        rateLimitRepository.save(notification);

        verify(mockedCache, times(1)).addWithExpiration(anyString(), anyLong());
    }

    @Test
    void testFindNotification() {
        Notification notification = new Notification(NotificationType.STATUS, "user1", "message1");

        rateLimitRepository.findNumberOfEntries(notification);

        verify(mockedCache, times(1)).findNumberOfEntries(notification.getRateLimitKey());
    }
}
