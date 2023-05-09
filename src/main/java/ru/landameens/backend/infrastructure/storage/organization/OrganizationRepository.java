package ru.landameens.backend.infrastructure.storage.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.landameens.backend.infrastructure.storage.organization.entity.OrganizationEntity;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
}
