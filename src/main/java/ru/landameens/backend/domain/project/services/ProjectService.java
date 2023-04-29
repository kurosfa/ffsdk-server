package ru.landameens.backend.domain.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.landameens.backend.api.exception.NotFoundException;
import ru.landameens.backend.domain.project.model.Project;
import ru.landameens.backend.infrastructure.storage.project.ProjectRepository;
import ru.landameens.backend.infrastructure.storage.project.entity.ProjectEntity;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project with such id not found")).toDomain();
    }

    public Project createProject(ProjectEntity project) {
        return projectRepository.save(project).toDomain();
    }

    public Project updateProject(ProjectEntity project) {
        return projectRepository.save(project).toDomain();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
