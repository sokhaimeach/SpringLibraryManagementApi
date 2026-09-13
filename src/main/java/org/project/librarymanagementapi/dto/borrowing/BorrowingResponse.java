package org.project.librarymanagementapi.dto.borrowing;

import java.time.LocalDate;

public class BorrowingResponse {
    private Long id;
    private Long bookCopyId;
    private String bookTitle;
    private Long memberId;
    private String memberName;
    private LocalDate borrowedAt;
    private LocalDate returnedAt;

    public BorrowingResponse(Long id, Long bookCopyId, String bookTitle, Long memberId, String memberName, LocalDate borrowedAt, LocalDate returnedAt) {
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

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDate getReturnedAt() {
        return returnedAt;
    }
}
