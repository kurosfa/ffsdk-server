package ru.landameens.backend.api.requirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.landameens.backend.domain.requirement.model.Requirement;
import ru.landameens.backend.domain.requirement.services.RequirementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/requirements")
@CrossOrigin
public class RequirementController {
    private final RequirementService requirementService;

    @Autowired
    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping("/by-project-id/{id}")
    public List<Requirement> getRequirementsByProjectId(@PathVariable Long id) {
        return requirementService.getRequirements(id);
    }

    @GetMapping("/{id}")
    public Requirement getRequirement(@PathVariable Long id) {
        return requirementService.getRequirement(id);
    }

    @GetMapping("/by-name/{name}")
    public Requirement getRequirementByName(@PathVariable String name) {
        return requirementService.getRequirementByName(name);
    }

    @PostMapping()
    public Requirement createRequirement(@Valid @RequestBody Requirement requirement) {
        return requirementService.createRequirement(requirement);
    }

    @PutMapping("/{id}")
    public Requirement updateRequirement(@Valid @RequestBody Requirement requirement) {
        return requirementService.updateRequirement(requirement);
    }

    @DeleteMapping("/{id}")
    public void deleteRequirement(@PathVariable Long id) {
        requirementService.deleteRequirement(id);
    }
}
