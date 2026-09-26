package org.project.librarymanagementapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends Person {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role")
    )
    private Set<Role> roles = new HashSet<Role>();

    private boolean enabled = true;
}
