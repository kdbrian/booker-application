package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company getCompanyById(String id);

    List<Company> getAllCompanies();

    Company addCompany(CompanyDTO dto);

    List<Company> addCompanies(List<CompanyDTO> companyDTOS);

    Company getCompanyByName(String name);

    List<Company> getCompanyByNameWildCard(String name);

    Company getCompanyByLocation(String name);

    List<Company> getCompanyByLocationNameWildCard(String name);

    List<Company> getCompanyByNameAndLocation(String name, String location);

    void deleteById(String companyID);

    Long deleteAll();


    //pagination
    Page<Company> findCompanies(Pageable pageable);

    List<Company> findCompaniesSortBy(Sort sort);

}
