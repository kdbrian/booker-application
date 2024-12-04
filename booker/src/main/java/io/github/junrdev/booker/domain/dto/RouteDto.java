package io.github.junrdev.booker.domain.dto;

import io.github.junrdev.booker.domain.model.Vehicle;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {

    private String id;

    @NotBlank(message = "From cannot be null.")
    private String from;

    @NotBlank(message = "From cannot be null.")
    private String to;

    @NotBlank(message = "Missing schedule id.")
    private String scheduleID;

    private String createdAt;

//    private Set<Vehicle> vehicles;


}
