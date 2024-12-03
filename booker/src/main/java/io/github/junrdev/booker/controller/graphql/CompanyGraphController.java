package io.github.junrdev.booker.controller.graphql;

import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CompanyGraphController {


    private final CompanyService companyService;

    @Autowired
    public CompanyGraphController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @QueryMapping//(name = "getCompanyById")
    public Company getCompanyById(
            @Argument String companyID
    ) {
        return companyService.getCompanyById(companyID);
    }

}
