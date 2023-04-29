package ru.landameens.backend.infrastructure.storage.requirement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.landameens.backend.infrastructure.storage.requirement.entity.RequirementEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementRepository extends JpaRepository<RequirementEntity, Long> {
    List<RequirementEntity> findAllByProjectId(Long projectId);
    Optional<RequirementEntity> findByName(String name);
}
