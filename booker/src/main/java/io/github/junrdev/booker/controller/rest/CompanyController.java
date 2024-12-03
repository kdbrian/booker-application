package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import io.github.junrdev.booker.util.error.AppError;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final Validator validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(CompanyService companyService, Validator validator) {
        this.companyService = companyService;
        this.validator = validator;
    }


    @PostMapping("/new")
    public ResponseEntity<Company> addCompany(@Valid @RequestBody CompanyDTO dto) {

        Set<ConstraintViolation<CompanyDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            String validatorError = violations.stream().map(v -> "\n" + v.getMessage()).collect(Collectors.joining());
            throw new AppError("Failed to validate request : Error \n" + validatorError, HttpStatus.BAD_REQUEST);
        }

        Company added = companyService.addCompany(dto);
        return new ResponseEntity<Company>(added, HttpStatus.CREATED);
    }


    @PostMapping("/add/all")
    public ResponseEntity<List<Company>> addCompany(@Valid @RequestBody List<CompanyDTO> companyDTOS) {
        List<Company> added = companyService.addCompanies(companyDTOS);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }


    @GetMapping(value = {"/"})
    public ResponseEntity<Page<Company>> getAllCompanies(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size
    ) {
        /*
        if (page != null) {
            if (page < 1)
                throw new AppError("Invalid page configuration", HttpStatus.BAD_REQUEST);

            //page and size
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(companyService.findCompanies(pageable));
        }

         */

        //only size
        return ResponseEntity.ok(companyService.findCompanies(Pageable.unpaged()));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<Company> getCompanyById(
            @PathVariable(value = "id") String companyID
    ) {
        return ResponseEntity.ok(companyService.getCompanyById(companyID));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable("name") String name) {
        Company _companys = companyService.getCompanyByName(name);
        return ResponseEntity.ok(_companys);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getLocationCompanies(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "name", required = false) String name
    ) {

        if (location != null && name != null) {
            LOGGER.debug("loc -> {}, name -> {}", location, name);
            return ResponseEntity.ok(companyService.getCompanyByNameAndLocation(name, location));
        }

        if (location != null && !location.isBlank()) {
            LOGGER.debug("loc -> {}", location);
            return ResponseEntity.ok(companyService.getCompanyByLocationNameWildCard(location));
        }

        if (name != null && !name.isBlank()) {
            LOGGER.debug("name -> {}", name);
            return ResponseEntity.ok(companyService.getCompanyByNameWildCard(name));
        }

        return ResponseEntity.ok(companyService.getCompanyByLocation(location));
    }


    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllCompanies() {
        return ResponseEntity.ok(String.format("Deleted %d records", companyService.deleteAll()));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAllCompanies(
            @PathVariable(value = "id") String id
    ) {
        companyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
