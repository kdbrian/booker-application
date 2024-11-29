package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    List<Schedule> getSchedules();

    List<Schedule> getCompanySchedules(String companyId);

    Schedule addSchedule(ScheduleDto dto);

    void deleteSchedule(Schedule schedule);
}
