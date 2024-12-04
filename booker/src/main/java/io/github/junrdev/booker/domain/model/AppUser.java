package io.github.junrdev.booker.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Builder
public class AppUser {

    @Id
    private String uid;

    private String name;

    private String email;

    private String phone;

    private String password;

    @Builder.Default
    private String dateJoined = LocalDateTime.now().toString();

    @Builder.Default
    private boolean isActive = true;

}
