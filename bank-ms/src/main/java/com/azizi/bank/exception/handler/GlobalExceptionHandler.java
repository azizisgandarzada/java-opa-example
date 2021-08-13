package com.azizi.bank.exception.handler;

import com.azizi.bank.exception.AccessDeniedException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, Object> handle(AccessDeniedException ex, WebRequest request) {
        log.error("Access denied exception", ex);
        Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        attributes.put("status", HttpStatus.FORBIDDEN.value());
        attributes.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
        attributes.put("message", ex.getMessage());
        attributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return attributes;
    }

}
