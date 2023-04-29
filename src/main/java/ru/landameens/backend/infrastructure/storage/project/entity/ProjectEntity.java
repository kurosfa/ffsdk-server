package ru.landameens.backend.infrastructure.storage.project.entity;

import lombok.*;
import ru.landameens.backend.domain.project.model.Project;
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
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="project")
    private Set<RequirementEntity> requirements;

    public static ProjectEntity fromDomain(Project project) {
        return new ProjectEntity(project.getId(), project.getName(), Collections.emptySet());
    }

    public Project toDomain() {
        return new Project(id, name);
    }
}
