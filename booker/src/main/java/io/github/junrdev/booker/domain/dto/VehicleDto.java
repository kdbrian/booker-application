package io.github.junrdev.booker.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
public record VehicleDto(

        String id,

        @NotBlank(message = "Provide a route ID")
        String routeId,

        @NotBlank(message = "We need a unique identifier. Like a plate Number.")
        String identifier,

        @NotNull(message = "What is the price")
        @Min(value = 200, message = "less than minimum value 199")
        @Max(value = 10000, message = "more than maximum 9999")
        Double price,

        List<String> features
) {

}
