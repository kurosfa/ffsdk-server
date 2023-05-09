package ru.landameens.backend.domain.organization.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Organization {
    private Long id;

    @NotNull
    @NotBlank
    private String name;
}
