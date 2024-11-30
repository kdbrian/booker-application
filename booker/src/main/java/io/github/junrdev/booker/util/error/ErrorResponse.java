package io.github.junrdev.booker.util.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
    transient private Throwable cause;


    ErrorResponse(int statusCode, String message){
        this.message =message;
        this.statusCode=statusCode;
    }

}
