package io.github.junrdev.booker.domain.dto;

import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.domain.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {

    private String id;

    private String from;

    private String to;

    private String scheduleID;

    private Set<Vehicle> vehicles;


}
