package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;

import java.util.List;

public interface CompanyService {

    Company getCompanyById(String id);

    List<Company> getAllCompanies();

    Company addCompany(CompanyDTO dto);

    List<Company> getCompanyByName(String name);

    List<Company> getCompanyByLocation(String name);

    void deleteById(String companyID);

    Long deleteAll();

}
