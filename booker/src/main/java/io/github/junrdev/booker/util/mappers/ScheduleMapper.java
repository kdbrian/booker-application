package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Schedule;
import org.springframework.stereotype.Component;


@Component
public abstract class ScheduleMapper {

    //1.baseline implementations can be changes where reference is created

    public ScheduleDto toScheduleDto(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .companyID(schedule.getCompany().getId())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .routes(schedule.getRoutes())
                .build();
    }

    public Schedule fromScheduleDto(ScheduleDto dto) {
        return Schedule.builder()
                .routes(dto.getRoutes())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
    }
}
