package com.portella.granjaconectada.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime localDateTime;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errors;

    public ErrorResponse(LocalDateTime now, int value, String reasonPhrase, String message, String contextPath) {
        this.localDateTime = now;
        this.status = value;
        this.error = reasonPhrase;
        this.message = message;
        this.path = contextPath;
    }
}
