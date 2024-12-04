package io.github.junrdev.booker.controller.graphql;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompanyGraphController {


    private final CompanyService companyService;

    @Autowired
    public CompanyGraphController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @QueryMapping
    public Company getCompanyById(@Argument String companyID) {
        return companyService.getCompanyById(companyID);
    }


    @QueryMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }


    @QueryMapping
    public Company getCompanyByLocation(@Argument String location) {
        return companyService.getCompanyByLocation(location);
    }


    @QueryMapping
    public List<Company> getCompaniesByLocation(@Argument String location) {
        return companyService.getCompanyByLocationNameWildCard(location);
    }

    @QueryMapping
    public Company getCompanyByName(@Argument String name) {
        return companyService.getCompanyByName(name);
    }

    @QueryMapping
    public List<Company> getCompaniesByName(@Argument String name) {
        return companyService.getCompanyByNameWildCard(name);
    }

    @MutationMapping
    public Company addCompany(@Argument CompanyDTO companyDTO) {
        return companyService.addCompany(companyDTO);
    }

    @MutationMapping
    public List<Company> addCompanies(@Argument List<CompanyDTO> companyDTO) {
        return companyService.addCompanies(companyDTO);
    }

    @MutationMapping
    public Boolean deleteAllCompanies() {
        assert companyService.deleteAll() != -1;
        return true;
    }

    @MutationMapping
    public Boolean deleteById(@Argument String companyId) {
        companyService.deleteById(companyId);
        return true;
    }


}
