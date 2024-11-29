package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    List<Company> getAllCompanies();

    Company addCompany(CompanyDTO dto);

    Company getCompanyByName(String name);

    List<Company> getCompanyByLocation(String name);

}
