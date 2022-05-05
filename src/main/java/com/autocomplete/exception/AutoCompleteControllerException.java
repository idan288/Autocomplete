package com.autocomplete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class AutoCompleteControllerException
{
        @ExceptionHandler(ResponseStatusException.class)
        ResponseEntity<?> handleStatusException(ResponseStatusException ex, HttpServletRequest request) {
            return ResponseEntity.status(ex.getStatus()).body(new Response(ex.getReason(), ex.getStatus(), request.getRequestURI()));
        }

        private static class Response
        {
            private final String timestamp;
            private final String message;
            private final HttpStatus status;
            private final String path;

            private Response(String message, HttpStatus status, String path)
            {
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("M/dd/yyyy - HH:mm:ss");
                this.timestamp  = ZonedDateTime.now().format(formatter2);;
                this.message    = message;
                this.status     = status;
                this.path       = path;
            }

            public String getTimestamp()  { return timestamp;  }
            public String getMessage()
            {
                return message;
            }
            public HttpStatus getStatus()
            {
                return status;
            }
            public String getPath() { return path; }
        }
}