package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Schedule;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class ScheduleMapper extends EntityToDtoMapper<Schedule, ScheduleDto> {

    //1.baseline implementations can be changes where reference is created

    @Override
    public Schedule fromDto(ScheduleDto dto) {
        return Schedule.builder()
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
    }

    @Override
    public ScheduleDto toDto(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .companyID(schedule.getCompany().getId())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .build();
    }
}


