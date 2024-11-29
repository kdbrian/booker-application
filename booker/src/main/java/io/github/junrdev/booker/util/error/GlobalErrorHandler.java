package io.github.junrdev.booker.util.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalErrorHandler {


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex){

        return new ResponseEntity<>(
                new ErrorResponse(ex.getStatusCode().value(), ex.getMessage(), ex.getCause()),
                ex.getStatusCode()
        );

    }

}
