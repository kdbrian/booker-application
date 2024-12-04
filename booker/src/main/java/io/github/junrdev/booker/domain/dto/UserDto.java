package io.github.junrdev.booker.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Max(message = "username can only be 8-20 characters long", value = 20)
    @Min(message = "username can only be 8-20 characters long", value = 8)
    private String name;

    @NotBlank(message = "username cannot be blank")

    private String email;

    private String phone;

    @Builder.Default
    private String dateJoined = LocalDateTime.now().toString();

    @Builder.Default
    private boolean isActive = true;
}
