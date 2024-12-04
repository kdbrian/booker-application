package io.github.junrdev.booker.controller.graphql;

import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.domain.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ScheduleGraphController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleGraphController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @QueryMapping
    public List<Schedule> getSchedules() {
        return scheduleService.getSchedules();
    }


    @QueryMapping
    public Schedule getScheduleById(@Argument String id) {
        return scheduleService.getScheduleById(id);
    }

    @QueryMapping
    public List<Schedule> getCompanySchedules(@Argument String companyId) {
        return scheduleService.getCompanySchedules(companyId);
    }

    @MutationMapping
    public Schedule addSchedule(@Argument ScheduleDto dto) {
        return scheduleService.addSchedule(dto);
    }

    @MutationMapping
    public List<Schedule> addSchedules(@Argument List<ScheduleDto> scheduleDtos) {
        return scheduleService.addSchedules(scheduleDtos);
    }

    @MutationMapping
    public boolean deleteAllSchedules() {
        scheduleService.deleteAll();
        return true;
    }

    @MutationMapping
    public boolean deleteScheduleById(String id) {
        scheduleService.deleteScheduleById(id);
        return true;
    }


}
