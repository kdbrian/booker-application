package io.github.junrdev.booker.domain.model;


import jdk.dynalink.linker.LinkerServices;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class Vehicle {

    @Id
    private String id;

    @DBRef
    private Route route;


    private Double price;

    private List<String> features;

}
