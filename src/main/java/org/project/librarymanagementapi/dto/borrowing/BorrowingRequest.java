package org.project.librarymanagementapi.dto.borrowing;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BorrowingRequest {
    @Positive
    @NotNull(message = "Book copy id cannot be null")
    private Long bookCopyId;

    @Positive
    @NotNull(message = "Member id cannot be null")
    private Long memberId;

    public BorrowingRequest(Long bookCopyId, Long memberId) {
        this.bookCopyId = bookCopyId;
        this.memberId = memberId;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
