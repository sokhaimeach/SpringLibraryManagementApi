package org.project.librarymanagementapi.dto.bookCopy;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookCopyRequest {
    @NotBlank(message = "Quanlity cannot be null or blank")
    @Min(value = 1, message = "Quality at least 1")
    private int quantity;

    @NotBlank(message = "Book ID cannot be null or blank")
    @NotNull(message = "book id cannot be null")
    private Long bookId;

    public BookCopyRequest(int quantity, Long bookId) {
        this.quantity = quantity;
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getBookId() {
        return bookId;
    }
}
