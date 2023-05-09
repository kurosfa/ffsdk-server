package ru.landameens.backend.api.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.landameens.backend.domain.organization.model.Organization;
import ru.landameens.backend.domain.organization.services.OrganizationService;
import ru.landameens.backend.infrastructure.storage.organization.entity.OrganizationEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/organizations")
@CrossOrigin
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{id}")
    public Organization getOrganization(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }

    @PostMapping()
    public Organization createOrganization(@Valid @RequestBody Organization organization) {
        return organizationService.createOrganization(OrganizationEntity.fromDomain(organization));
    }

    @PutMapping("/{id}")
    public Organization updateOrganization(@Valid @RequestBody Organization organization) {
        return organizationService.updateOrganization(OrganizationEntity.fromDomain(organization));
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }
}

