package ru.landameens.backend.domain.requirement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class Requirement {
    private Long id;

    @NotNull
    @Positive
    private Long projectId;

    @NotNull
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Boolean status;
}
