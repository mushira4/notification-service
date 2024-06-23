package com.mybank.notification.input.rest.doc;

import com.mybank.notification.input.rest.dto.NotificationCreateRequestDTO;
import com.mybank.notification.input.rest.dto.NotificationCreateResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

public interface NotificationRestControllerDoc {

    @Operation(
            summary = "Send notifications",
            description =
                    """ 
Send notifications to the user considering the notification type and the user id on the rate limit. \n
The notification type can be news, update, marketing or etc(any other)
                    """,
            method = "POST",
            tags = {"notification"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Notification request",
                    required = true,
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example of notification request",
                                            value = """
{
    "type": "news",
    "userId": "123",
    "message": "Hello, this is a news message"
}
                                                    """
                                    )
                            }
                    )
            )
    )
    @SuppressWarnings("unused")
    NotificationCreateResponseDTO sendNotification(NotificationCreateRequestDTO request);
}
