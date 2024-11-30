package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import io.github.junrdev.booker.repo.mongo.CompanyRepository;
import io.github.junrdev.booker.util.error.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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
    public List<Company> getCompanyByName(String name) {
        return  companyRepository.findByName(name);
    }

    @Override
    public List<Company> getCompanyByLocation(String name) {
        return companyRepository.findByLocation(name);
    }

    @Override
    public void deleteById(String companyID) {
        if (!companyRepository.existsById(companyID))
            throw new AppError("Missing company with id : "+companyID, HttpStatus.NOT_FOUND);

        companyRepository.deleteById(companyID);
    }

    @Override
    public Long deleteAll() {
        long count = companyRepository.count();
        companyRepository.deleteAll();
        return count;
    }


}
