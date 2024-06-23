package com.mybank.notification.input.rest;

import com.mybank.notification.core.exception.NotificationRateLimitException;
import com.mybank.notification.input.rest.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class NotificationRestExceptionHandler {
    @ExceptionHandler({ NotificationRateLimitException.class })
    public final ResponseEntity<ApiErrorDTO> handleException(Exception ex, WebRequest request) {
            ApiErrorDTO body = new ApiErrorDTO(ex.getMessage());
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleException(ex, body, status, request);
    }

    protected ResponseEntity<ApiErrorDTO> handleException(
            Exception ex,
            @Nullable ApiErrorDTO body,
            HttpStatus status,
            WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, status);
    }
}
