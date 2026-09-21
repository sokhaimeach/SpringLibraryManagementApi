package org.project.librarymanagementapi.controllers;

import jakarta.validation.Valid;
import org.project.librarymanagementapi.dto.ApiResponse;
import org.project.librarymanagementapi.dto.author.AuthorRequest;
import org.project.librarymanagementapi.dto.author.AuthorResponse;
import org.project.librarymanagementapi.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AuthorResponse>>> getAllAuthors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String name
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Authors fetched successfully",
                        authorService.getAll(page, size, name)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Author fetched successfully",
                        authorService.getById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponse>> createAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Author created successfully",
                        authorService.create(authorRequest),
                        HttpStatus.CREATED
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequest authorRequest
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Author updated successfully",
                        authorService.update(id, authorRequest)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>("Author deleted successfully"));
    }

}
