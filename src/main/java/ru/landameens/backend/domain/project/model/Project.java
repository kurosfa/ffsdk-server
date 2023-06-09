package ru.landameens.backend.domain.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class Project {
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Long organizationId;
}
