package ru.landameens.backend.infrastructure.storage.organization.entity;

import lombok.*;
import ru.landameens.backend.domain.organization.model.Organization;
import ru.landameens.backend.infrastructure.storage.project.entity.ProjectEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "organizations")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="organization")
    private Set<ProjectEntity> projects;

    public static OrganizationEntity fromDomain(Organization organization) {
        return new OrganizationEntity(organization.getId(), organization.getName(), Collections.emptySet());
    }

    public Organization toDomain() {
        return new Organization(id, name);
    }
}
