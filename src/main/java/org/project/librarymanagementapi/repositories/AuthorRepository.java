package org.project.librarymanagementapi.repositories;

import org.project.librarymanagementapi.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
