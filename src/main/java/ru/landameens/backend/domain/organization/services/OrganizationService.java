package ru.landameens.backend.domain.organization.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.landameens.backend.api.exception.NotFoundException;
import ru.landameens.backend.domain.organization.model.Organization;
import ru.landameens.backend.infrastructure.storage.organization.OrganizationRepository;
import ru.landameens.backend.infrastructure.storage.organization.entity.OrganizationEntity;


@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization getOrganization(Long id) {
        return organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Project with such id not found")).toDomain();
    }

    public Organization createOrganization(OrganizationEntity organization) {
        return organizationRepository.save(organization).toDomain();
    }

    public Organization updateOrganization(OrganizationEntity organization) {
        return organizationRepository.save(organization).toDomain();
    }

    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }
}
