package io.github.junrdev.booker.domain.dto;

import io.github.junrdev.booker.domain.model.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private String companyID;

    private Long startTime;

    private Long endTime;

}
