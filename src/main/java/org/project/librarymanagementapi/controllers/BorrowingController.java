package org.project.librarymanagementapi.controllers;

import jakarta.validation.Valid;
import org.project.librarymanagementapi.dto.ApiResponse;
import org.project.librarymanagementapi.dto.borrowing.BorrowingRequest;
import org.project.librarymanagementapi.dto.borrowing.BorrowingResponse;
import org.project.librarymanagementapi.services.BorrowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BorrowingController {
    private final BorrowingService borrowingService;
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BorrowingResponse>>> getAllBorrowings() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Borrowings fetched successfully",
                        this.borrowingService.getAllBorrowings()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BorrowingResponse>> getBorrowing(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Borrowing fetched successfully",
                        this.borrowingService.getBorrowingById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BorrowingResponse>> createBorrowing(@Valid @RequestBody BorrowingRequest borrowingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Borrowing created successfully",
                        this.borrowingService.createBorrowing(borrowingRequest),
                        HttpStatus.CREATED
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BorrowingResponse>> returnedBook(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Borrowing returned successfully",
                        this.borrowingService.returnBorrowing(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteBorrowing(@PathVariable Long id) {
        borrowingService.deleteBorrowing(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Borrowing deleted successfully"
                )
        );
    }

}
