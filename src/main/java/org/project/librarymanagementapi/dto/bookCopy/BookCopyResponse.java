package org.project.librarymanagementapi.dto.bookCopy;

public class BookCopyResponse {

    private Long id;
    private String copyNumber;
    private Boolean status;
    private Long  bookId;
    private String bookTitle;

    public BookCopyResponse(Long id, String copyNumber, Boolean status, Long bookId, String bookTitle) {
        this.id = id;
        this.copyNumber = copyNumber;
        this.status = status;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    public Long getId() {
        return id;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
