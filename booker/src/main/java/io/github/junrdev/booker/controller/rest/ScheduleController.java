package io.github.junrdev.booker.controller.rest;


import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.domain.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/schedule")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Schedule>> getSchedules(){
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    @GetMapping("/company/{companyID}/")
    public ResponseEntity<List<Schedule>> getCompanySchedules(
            @PathVariable(value = "companyID") String companyID
    ){
        return ResponseEntity.ok(scheduleService.getCompanySchedules(companyID));
    }


    @PostMapping("/new")
    public ResponseEntity<Schedule> addSchedule(
            @RequestBody ScheduleDto dto
            ){
        return new ResponseEntity<>(scheduleService.addSchedule(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<Void> deleteSchedule(
            @RequestBody Schedule schedule
    ){
        scheduleService.deleteSchedule(schedule);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
