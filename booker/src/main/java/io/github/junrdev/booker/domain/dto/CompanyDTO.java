package io.github.junrdev.booker.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @NotNull(message = "name is required")
    @Size(
            max = 60,
            message = "name.size < 0 && 60 < name.size"
    )
    private String name;

    @NotNull(message = "location is required")
    @Size(
            max = 30,
            message = "name.size < 0 && 30 < name.size"
    )private String location;

}
