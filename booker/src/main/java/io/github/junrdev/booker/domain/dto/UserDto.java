package io.github.junrdev.booker.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {

    private String uid;


    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Max(message = "username can only be 4-20 characters long", value = 20)
    @Min(message = "username can only be 4-20 characters long", value = 4)
    private String name;

    @NotBlank(message = "username cannot be blank")
    @Email(message = "email is of invalid format")
    private String email;

    private String phone;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Max(message = "password can only be 6-12 characters long", value = 20)
    @Min(message = "password can only be 6-12 characters long", value = 6)
    private String password;

    @Builder.Default
    private String dateJoined = LocalDateTime.now().toString();

    @Builder.Default
    private boolean isActive = true;
}
