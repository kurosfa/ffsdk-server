package ru.landameens.backend.infrastructure.storage.project.entity;

import lombok.*;
import ru.landameens.backend.domain.project.model.Project;
import ru.landameens.backend.infrastructure.storage.organization.entity.OrganizationEntity;
import ru.landameens.backend.infrastructure.storage.requirement.entity.RequirementEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="organization_id", nullable=false)
    private OrganizationEntity organization;

    @OneToMany(mappedBy="project")
    private Set<RequirementEntity> requirements;

    public static ProjectEntity fromDomain(Project project, OrganizationEntity organization) {
        return new ProjectEntity(project.getId(), project.getName(), organization, Collections.emptySet());
    }

    public Project toDomain() {
        return new Project(id, name, organization.getId());
    }
}
