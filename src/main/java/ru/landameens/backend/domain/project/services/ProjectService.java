package ru.landameens.backend.domain.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.landameens.backend.api.exception.InvalidDataException;
import ru.landameens.backend.api.exception.NotFoundException;
import ru.landameens.backend.domain.project.model.Project;
import ru.landameens.backend.infrastructure.storage.organization.OrganizationRepository;
import ru.landameens.backend.infrastructure.storage.project.ProjectRepository;
import ru.landameens.backend.infrastructure.storage.project.entity.ProjectEntity;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, OrganizationRepository organizationRepository) {
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project with such id not found")).toDomain();
    }

    public Project createProject(Project project) {
        var organization = organizationRepository.findById(project.getOrganizationId()).orElseThrow(InvalidDataException::new);
        return projectRepository.save(ProjectEntity.fromDomain(project, organization)).toDomain();
    }

    public Project updateProject(Project project) {
        var organization = organizationRepository.findById(project.getOrganizationId()).orElseThrow(InvalidDataException::new);
        return projectRepository.save(ProjectEntity.fromDomain(project, organization)).toDomain();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
