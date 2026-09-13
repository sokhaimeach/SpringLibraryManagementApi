package org.project.librarymanagementapi.services;


import org.project.librarymanagementapi.dto.book.BookRequest;
import org.project.librarymanagementapi.dto.book.BookResponse;
import org.project.librarymanagementapi.entities.Author;
import org.project.librarymanagementapi.entities.Book;
import org.project.librarymanagementapi.exceptions.ResourceNotFoundException;
import org.project.librarymanagementapi.repositories.AuthorRepository;
import org.project.librarymanagementapi.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(
            BookRepository bookRepository,
            AuthorRepository authorRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public BookResponse create(BookRequest bookRequest) {
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(
                () -> new ResourceNotFoundException(
                        "Cannot create book because author with id "
                                + bookRequest.getAuthorId() + " not found"
                )
        );

        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());
        book.setPublishedYear(bookRequest.getPublicationYear());
        book.setAuthor(author);

        bookRepository.save(book);
        return toResponse(book);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Book with id " + id + " not found")
                );

        return toResponse(book);
    }

    @Transactional
    public BookResponse update(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));

        Author existingAuthor = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));

        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());
        book.setPublishedYear(bookRequest.getPublicationYear());
        book.setAuthor(existingAuthor);

        bookRepository.save(book);
        return toResponse(book);
    }

    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }

        bookRepository.deleteById(id);
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPrice(),
                book.getPublishedYear(),
                book.getAuthor().getId(),
                book.getAuthor().getName()
        );
    }
}
