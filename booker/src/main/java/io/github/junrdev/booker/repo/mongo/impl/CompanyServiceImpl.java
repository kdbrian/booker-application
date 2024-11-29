package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import io.github.junrdev.booker.repo.mongo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Company> getCompanyByName(String name) {
        return  companyRepository.findByName(name);
    }

    @Override
    public List<Company> getCompanyByLocation(String name) {
        return companyRepository.findByLocation(name);
    }


}
