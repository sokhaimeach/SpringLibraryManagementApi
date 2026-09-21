package org.project.librarymanagementapi.repositories;

import org.project.librarymanagementapi.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query(value = """
    select b from Book b
        where lower(b.title) like lower(concat('%', trim(:title), '%'))
    """)
    Page<Book> searchByBookTitle(String title, Pageable pageable);
}
