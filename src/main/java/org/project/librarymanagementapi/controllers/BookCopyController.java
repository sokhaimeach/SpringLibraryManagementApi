package org.project.librarymanagementapi.controllers;

import jakarta.validation.Valid;
import org.project.librarymanagementapi.dto.ApiResponse;
import org.project.librarymanagementapi.dto.bookCopy.BookCopyRequest;
import org.project.librarymanagementapi.dto.bookCopy.BookCopyResponse;
import org.project.librarymanagementapi.services.BookCopyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-copies")
public class BookCopyController {
    private final BookCopyService bookCopyService;
    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<BookCopyResponse>>> addBookCopy(@Valid @RequestBody BookCopyRequest bookCopy) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Book copies created successfully",
                        bookCopyService.creates(bookCopy),
                        HttpStatus.CREATED
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteBookCopy(@PathVariable long id) {
        bookCopyService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>("Book copy deleted successfully")
        );
    }
}
