package org.project.librarymanagementapi.services;

import org.project.librarymanagementapi.dto.bookCopy.BookCopyRequest;
import org.project.librarymanagementapi.dto.bookCopy.BookCopyResponse;
import org.project.librarymanagementapi.entities.Book;
import org.project.librarymanagementapi.entities.BookCopy;
import org.project.librarymanagementapi.exceptions.ConflictException;
import org.project.librarymanagementapi.exceptions.ResourceNotFoundException;
import org.project.librarymanagementapi.repositories.BookCopyRepository;
import org.project.librarymanagementapi.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookCopyService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<BookCopyResponse> creates(BookCopyRequest bookCopyRequest) {
        Book book = bookRepository.findById(bookCopyRequest.getBookId()).orElseThrow(
                () -> new ResourceNotFoundException("Book with id: " + bookCopyRequest.getBookId() + " not found")
        );

        BookCopy lastBookCopy = bookCopyRepository.findFirstByOrderByIdDesc().orElse(null);

        Long lastBookCopyId = lastBookCopy == null ? 0L : lastBookCopy.getId();

        List<BookCopy> copies = new ArrayList<>();
        for (int i = 1; i <= bookCopyRequest.getQuantity();  i++) {
            // generate copy number
            String copyNumber = String.format("BOOK-%06d", (lastBookCopyId + i));

            BookCopy bookCopy = new BookCopy();
            bookCopy.setCopyNumber(copyNumber);
            bookCopy.setStatus(true);
            bookCopy.setBook(book);

            copies.add(bookCopy);
        }

        List<BookCopy> savedCopies = bookCopyRepository.saveAll(copies);

        return savedCopies.stream().map(this::toResponse).toList();
    }

    public void delete(Long id) {
        BookCopy bookCopy = bookCopyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id: " + id + " not found")
        );

        if (!bookCopy.getStatus()) {
            throw new ConflictException(
                    "Cannot delete this book copy because it is currently borrowed"
            );
        }

        bookCopyRepository.deleteById(id);
    }

    private BookCopyResponse toResponse(BookCopy bookCopy) {
        return new BookCopyResponse(
                bookCopy.getId(),
                bookCopy.getCopyNumber(),
                bookCopy.getStatus(),
                bookCopy.getBook().getId(),
                bookCopy.getBook().getTitle()
        );
    }
}
