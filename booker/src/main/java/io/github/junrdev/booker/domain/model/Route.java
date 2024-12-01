package io.github.junrdev.booker.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
@Builder
public class Route {

    @Id
    private String id;

    private String from;

    private String to;

    @DBRef
    private Schedule schedule;

    @Builder.Default
    private String createdAt = LocalDateTime.now().toString();
}
