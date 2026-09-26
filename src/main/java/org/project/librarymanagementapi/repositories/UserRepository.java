package org.project.librarymanagementapi.repositories;

import org.project.librarymanagementapi.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // use entityGraph to join table user_roles when fetch user
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);
}
