package ru.landameens.backend.api.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.landameens.backend.domain.project.model.Project;
import ru.landameens.backend.domain.project.services.ProjectService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    @PostMapping()
    public Project createProject(@Valid @RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@Valid @RequestBody Project project) {
        return projectService.updateProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}

