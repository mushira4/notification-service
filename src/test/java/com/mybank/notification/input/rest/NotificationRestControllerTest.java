package com.mybank.notification.input.rest;

import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.core.service.NotificationService;
import com.mybank.notification.core.service.dto.NotificationServiceOutput;
import com.mybank.notification.input.rest.dto.NotificationCreateRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void sendNotification() throws Exception {
        NotificationCreateRequestDTO request
                = new NotificationCreateRequestDTO("news", "user1", "message");

        when(notificationService.send(NotificationType.NEWS, "user1", "message"))
                .thenReturn(new NotificationServiceOutput(NotificationType.NEWS, "user1", "message", 1));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/notification")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());


    }
}
