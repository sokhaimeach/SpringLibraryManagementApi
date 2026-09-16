package org.project.librarymanagementapi.dto.borrowing;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BorrowingResponse {
    private Long id;
    private Long bookCopyId;
    private String bookTitle;
    private Long memberId;
    private String memberName;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

    public BorrowingResponse(Long id, Long bookCopyId, String bookTitle, Long memberId, String memberName, LocalDateTime borrowedAt, LocalDateTime returnedAt) {
        this.id = id;
        this.bookCopyId = bookCopyId;
        this.bookTitle = bookTitle;
        this.memberId = memberId;
        this.memberName = memberName;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }
}
