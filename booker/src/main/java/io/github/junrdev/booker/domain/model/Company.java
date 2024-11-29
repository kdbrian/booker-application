package io.github.junrdev.booker.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company  {

    @Id private String id;

    private String name;

    private String location;

    private String dateJoined = LocalDate.now().toString();

}
