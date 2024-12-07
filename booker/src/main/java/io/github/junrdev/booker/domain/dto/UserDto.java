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

    @NotBlank(message = "username cannot be blank")
    @Size(min = 4, max = 20, message = "username must be between 4 and 20 characters")
    private String name;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "email is of invalid format")
    private String email;

    @NotBlank(message = "phone number cannot be blank")
    @Pattern(
            regexp = "^(254|\\+254|0)?(7\\d{8}|1\\d{8})$",
            message = "phone number must be a valid Kenyan number starting with 07, 01, or 254"
    )
    private String phone;

    @NotBlank(message = "username cannot be blank")
    @Size(min = 6, max = 20, message = "username must be between 4 and 20 characters")
    private String password;

    @Builder.Default
    private String dateJoined = LocalDateTime.now().toString();

    @Builder.Default
    private boolean isActive = true;
}
