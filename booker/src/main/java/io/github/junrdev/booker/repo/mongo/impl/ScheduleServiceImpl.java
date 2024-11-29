package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.domain.service.ScheduleService;
import io.github.junrdev.booker.repo.mongo.CompanyRepository;
import io.github.junrdev.booker.repo.mongo.ScheduleRepository;
import io.github.junrdev.booker.util.error.AppError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, CompanyRepository companyRepository) {
        this.scheduleRepository = scheduleRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getCompanySchedules(String companyId) {

        Optional<Company> _company = companyRepository.findById(companyId);

        if (_company.isEmpty())
            throw new AppError(String.format("Missing company with id : %s",companyId), HttpStatus.NOT_FOUND);

        return scheduleRepository.findByCompany(_company.get());
    }

    @Override
    public Schedule addSchedule(ScheduleDto dto) {


        Optional<Company> _company = companyRepository.findById(dto.getCompanyID());

        if (_company.isEmpty())
            throw new AppError(String.format("Missing company with id : %s",dto.getCompanyID()), HttpStatus.NOT_FOUND);

        Schedule schedule = Schedule.builder()
                .company(_company.get())
                .endTime(dto.getEndTime())
                .startTime(dto.getStartTime())
                .build();


        if (!dto.getRoutes().isEmpty())
            schedule.addRoutes(dto.getRoutes().stream().toList());
        return scheduleRepository.save(
                schedule
        );
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }
}
