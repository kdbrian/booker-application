package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.model.Company;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyMappers extends EntityToDtoMapper<Company, CompanyDTO> {

    @Override
    public Company fromDto(CompanyDTO companyDTO) {
        return Company.builder()
                .name(companyDTO.getName())
                .location(companyDTO.getLocation())
                .dateJoined(LocalDateTime.now().toString())
                .build();
    }

    @Override
    public CompanyDTO toDto(Company company) {
        return CompanyDTO.builder()
                .name(company.getName())
                .location(company.getLocation())
                .build();
    }
}
