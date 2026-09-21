package org.project.librarymanagementapi.controllers;

import jakarta.validation.Valid;
import org.project.librarymanagementapi.dto.ApiResponse;
import org.project.librarymanagementapi.dto.book.BookRequest;
import org.project.librarymanagementapi.dto.book.BookResponse;
import org.project.librarymanagementapi.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BookResponse>>> getBooks(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Books fetched successfully",
                bookService.findAll(title, page, size)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Book fetched successfully",
                bookService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> createBook(@Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Book created successfully",
                        bookService.create(bookRequest),
                        HttpStatus.CREATED
                )
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<BookResponse>> updateBook(
            Long id, @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Book updated successfully",
                bookService.update(id, bookRequest)
        ));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Boolean>> deleteBook(Long id) {
        bookService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(
                "Book fetched successfully"
        ));
    }
}
