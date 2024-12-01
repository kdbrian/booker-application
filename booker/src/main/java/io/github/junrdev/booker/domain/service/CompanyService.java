package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;

import java.util.List;

public interface CompanyService {

    Company getCompanyById(String id);

    List<Company> getAllCompanies();

    Company addCompany(CompanyDTO dto);

    Company getCompanyByName(String name);

    List<Company> getCompanyByNameWildCard(String name);

    List<Company> getCompanyByLocation(String name);

    List<Company> getCompanyByLocationNameWildCard(String name);

    List<Company> getCompanyByNameAndLocation(String name,String location);

    void deleteById(String companyID);

    Long deleteAll();

}
