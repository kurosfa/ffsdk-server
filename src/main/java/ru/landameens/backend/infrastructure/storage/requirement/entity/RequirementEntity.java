package ru.landameens.backend.infrastructure.storage.requirement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.landameens.backend.domain.requirement.model.Requirement;
import ru.landameens.backend.infrastructure.storage.project.entity.ProjectEntity;

import javax.persistence.*;

@Entity
@Table(name = "requirements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private ProjectEntity project;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean status;

    public static RequirementEntity fromDomain(Requirement requirement, ProjectEntity project) {
        return new RequirementEntity(requirement.getId(), project, requirement.getName(), requirement.getDescription(), requirement.getStatus());
    }

    public Requirement toDomain() {
        return new Requirement(id, project.getId(), name, description, status);
    }
}
