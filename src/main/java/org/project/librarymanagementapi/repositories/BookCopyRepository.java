package org.project.librarymanagementapi.repositories;

import org.project.librarymanagementapi.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy,Long> {

    Optional<BookCopy> findFirstByOrderByIdDesc();
}
