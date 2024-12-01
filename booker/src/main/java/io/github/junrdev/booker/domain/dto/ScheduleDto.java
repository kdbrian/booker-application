package io.github.junrdev.booker.domain.dto;

import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.model.Route;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private String id;

    @NotBlank(message = "Company Id is required.")
    private String companyID;

    @Builder.Default
    private Long startTime = System.currentTimeMillis();

    @Builder.Default
    private Long endTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000;

}

