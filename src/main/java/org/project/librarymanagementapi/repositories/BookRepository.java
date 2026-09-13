package org.project.librarymanagementapi.repositories;

import org.project.librarymanagementapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
