package io.github.junrdev.booker.controller.rest;


import com.sun.source.tree.BreakTree;
import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.domain.service.ScheduleService;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.ScheduleMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequestMapping("/schedule")
@RestController
public class ScheduleController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;

    private final ScheduleMapper scheduleMapper;

    protected enum SORT_ORDER {
        ASC, DESC
    }

    @Autowired
    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<ScheduleDto>> getSchedules(
            @RequestParam(name = "sort", required = false) String sortBy,
            @RequestParam(name = "sort-order", required = false) String sortOrder
    ) {
        var sortByValues = new HashSet<String>(
                List.of(new String[]{"id", "startTime", "endTime", "noOfRoutes"})
        );

        List<Schedule> schedules = scheduleService.getSchedules();

        if (sortBy != null) {
            if (sortByValues.contains(sortBy)) {

                String sortValue = sortByValues.stream().filter(sort -> Objects.equals(sort, sortBy))
                        .iterator()
                        .next();

                var modified = switch (sortValue) {
                    case "id" ->
                            schedules.stream().sorted(Schedule.IDComparator).map(scheduleMapper::toScheduleDto).toList();
                    case "startTime" ->
                            schedules.stream().sorted(Schedule.StartTimeComparator).map(scheduleMapper::toScheduleDto).toList();
                    case "endTime" ->
                            schedules.stream().sorted(Schedule.EndTimeComparator).map(scheduleMapper::toScheduleDto).toList();
                    case "noOfRoutes" ->
                            schedules.stream().sorted(Schedule.RoutesCountComparator).map(scheduleMapper::toScheduleDto).toList();
                    default -> throw new IllegalStateException("Unexpected value: " + sortValue);
                };
                ResponseEntity.ok(modified.stream().toList());
            } else {
                throw new AppError("Missing sort field available are " + Arrays.toString(sortByValues.toArray()), HttpStatus.BAD_REQUEST);
            }

        }
        if (sortOrder != null) {
            LOGGER.debug("sortOrder {}", sortOrder);
        }
        return ResponseEntity.ok(schedules.stream().map(scheduleMapper::toScheduleDto).toList());
    }

    @GetMapping("/company/{companyID}/")
    public ResponseEntity<List<ScheduleDto>> getCompanySchedules(
            @PathVariable(value = "companyID") String companyID
    ) {
        return ResponseEntity
                .ok(scheduleService.getCompanySchedules(companyID)
                        .stream()
                        .map(scheduleMapper::toScheduleDto).toList());
    }

    @GetMapping("/{scheduleID}/")
    public ResponseEntity<ScheduleDto> getScheduleById(
            @PathVariable(value = "scheduleID") String scheduleID
    ) {
        return ResponseEntity.ok(scheduleMapper.toScheduleDto(scheduleService.getScheduleById(scheduleID)));
    }


    @PostMapping("/new")
    public ResponseEntity<ScheduleDto> addSchedule(
            @Valid @RequestBody ScheduleDto dto
    ) {
        return new ResponseEntity<>(scheduleMapper.toScheduleDto(scheduleService.addSchedule(dto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable(value = "id") String scheduleID
    ) {
        scheduleService.deleteScheduleById(scheduleID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll() {
        int size = scheduleService.getSchedules().size();
        scheduleService.deleteAll();
        return ResponseEntity.ok(String.format("Successfully deleted %d records.", size));
    }


}
