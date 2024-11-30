package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import io.github.junrdev.booker.util.error.AppError;
import jakarta.validation.*;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping("/new")
    public ResponseEntity<Company> addCompany(@Valid @RequestBody CompanyDTO dto) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CompanyDTO>> violations = validator.validate(dto);
        LOGGER.debug("Empty : {} 😂 {} violations 🤯🤯.", violations.isEmpty(), violations.size());

        if (!violations.isEmpty()) {
            String validatorError = violations.stream().map(v -> "\n" + v.getMessage()).collect(Collectors.joining());
            throw new AppError("Failed to validate request : Error \n" + validatorError, HttpStatus.BAD_REQUEST);
        }

        Company added = companyService.addCompany(dto);
        return new ResponseEntity<Company>(added, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{name}/")
    public ResponseEntity<List<Company>> getCompanyByName(@PathVariable("name") String name) {
        List<Company> _companys = companyService.getCompanyByName(name);
        return ResponseEntity.ok(_companys);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getLocationCompanies(@RequestParam(value = "location") String location) {
        LOGGER.debug("loc -> {}",location);
        return ResponseEntity.ok(companyService.getCompanyByLocation(location));
    }

}
