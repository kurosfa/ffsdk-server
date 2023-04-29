package ru.landameens.backend.domain.requirement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.landameens.backend.api.exception.InvalidDataException;
import ru.landameens.backend.api.exception.NotFoundException;
import ru.landameens.backend.domain.requirement.model.Requirement;
import ru.landameens.backend.infrastructure.storage.project.ProjectRepository;
import ru.landameens.backend.infrastructure.storage.requirement.RequirementRepository;
import ru.landameens.backend.infrastructure.storage.requirement.entity.RequirementEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository, ProjectRepository projectRepository) {
        this.requirementRepository = requirementRepository;
        this.projectRepository = projectRepository;
    }

    public Requirement getRequirement(Long id) {
        return requirementRepository.findById(id).orElseThrow(() -> new NotFoundException("Requirement with such id not found")).toDomain();
    }

    public Requirement getRequirementByName(String name) {
        return requirementRepository.findByName(name).orElseThrow(() -> new NotFoundException("Requirement with such id not found")).toDomain();
    }

    public List<Requirement> getRequirements(Long id) {
        return requirementRepository.findAllByProjectId(id).stream().map(RequirementEntity::toDomain).collect(Collectors.toList());
    }

    public Requirement createRequirement(Requirement requirement) {
        var project = projectRepository.findById(requirement.getProjectId()).orElseThrow(InvalidDataException::new);
        return requirementRepository.save(RequirementEntity.fromDomain(requirement, project)).toDomain();
    }

    public Requirement updateRequirement(Requirement requirement) {
        var project = projectRepository.findById(requirement.getProjectId()).orElseThrow(NotFoundException::new);
        return requirementRepository.save(RequirementEntity.fromDomain(requirement, project)).toDomain();
    }

    public void deleteRequirement(Long id) {
        requirementRepository.deleteById(id);
    }
}
