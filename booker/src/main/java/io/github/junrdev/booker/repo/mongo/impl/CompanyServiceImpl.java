package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import io.github.junrdev.booker.repo.mongo.CompanyRepository;
import io.github.junrdev.booker.repo.mongo.ScheduleRepository;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.CompanyMappers;
import io.github.junrdev.booker.util.mappers.ScheduleMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ScheduleRepository scheduleRepository;
    private final CompanyMappers companyMappers;


    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, CompanyMappers companyMappers) {
        this.companyRepository = companyRepository;
        this.scheduleRepository = scheduleRepository;
        this.companyMappers = companyMappers;
    }

    @Override
    public Company getCompanyById(String companyID) {
        if (!ObjectId.isValid(companyID))
            throw new AppError("Invalid id parameter : " + companyID, HttpStatus.NOT_FOUND);

        Optional<Company> _company = companyRepository.findById(companyID);
        if (!companyRepository.existsById(companyID) || _company.isEmpty())
            throw new AppError("Missing company with id : " + companyID, HttpStatus.NOT_FOUND);

        return _company.get();
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(CompanyDTO dto) {
        return companyRepository.save(
                Company.builder()
                        .name(dto.getName())
                        .location(dto.getLocation())
                        .build()
        );
    }

    @Override
    public List<Company> addCompanies(List<CompanyDTO> companyDTOS) {
        return companyRepository.saveAll(companyDTOS.stream().map(companyMappers::fromDto).toList());
    }

    @Override
    public Company getCompanyByName(String name) {
        try {
            Optional<Company> _company = companyRepository.findByName(name);
            if (_company.isEmpty())
                throw new AppError(String.format("Missing company %s", name), HttpStatus.NOT_FOUND);
            return _company.get();
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new AppError(String.format("Multiple matches found visit [/?name=\"%s\"]", name), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Company> getCompanyByNameWildCard(String name) {
        return companyRepository.findByNameContaining(name);
    }

    @Override
    public List<Company> getCompanyByLocationNameWildCard(String name) {
        return companyRepository.findByLocationContaining(name);
    }

    @Override
    public List<Company> getCompanyByNameAndLocation(String name, String location) {
        return companyRepository.findByNameAndLocationContaining(name, location);
    }

    @Override
    public Company getCompanyByLocation(String name) {
        return companyRepository
                .findByLocation(name)
                .orElseThrow(() -> new AppError("Missing company with name : " + name, HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteById(String companyID) {
        if (!ObjectId.isValid(companyID))
            throw new AppError("Invalid id parameter : " + companyID, HttpStatus.NOT_FOUND);

        Optional<Company> _company = companyRepository.findById(companyID);
        if (_company.isEmpty())
            throw new AppError("Missing company with id : " + companyID, HttpStatus.NOT_FOUND);

        companyRepository.deleteById(companyID);
    }

    @Override
    public Long deleteAll() {
        long count = companyRepository.count();
        companyRepository.deleteAll();
        return count;
    }

    @Override
    public Page<Company> findCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public List<Company> findCompaniesSortBy(Sort sort) {
        return companyRepository.findAll(sort);
    }


}
