package io.github.junrdev.booker.util.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = false)
@Data
public class AppError extends RuntimeException {

  private int statusCode;

  public AppError(String message, HttpStatus statusCode) {
    super(message);
    this.statusCode = statusCode.value();
  }

}
