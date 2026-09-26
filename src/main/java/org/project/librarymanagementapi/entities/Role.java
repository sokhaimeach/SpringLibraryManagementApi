package org.project.librarymanagementapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
}
