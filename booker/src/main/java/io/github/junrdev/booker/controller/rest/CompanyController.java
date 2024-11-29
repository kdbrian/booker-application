package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping("/new")
    public ResponseEntity<Company> addCompany(@RequestBody CompanyDTO dto){
        Company added = companyService.addCompany(dto);
        return new ResponseEntity<Company>(added, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{name}/")
    public ResponseEntity<Company> getCompanyByName(@PathVariable("name") String name){
        Optional<Company> _company = companyService.getCompanyByName(name);
        return _company.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Company>> getLocationCompanies(@RequestParam(value = "loc", required = true) String location){
        if (location!=null){
            return ResponseEntity.ok(companyService.getCompanyByLocation(location));
        }else
            return ResponseEntity.ok(new ArrayList<>());
    }

}
