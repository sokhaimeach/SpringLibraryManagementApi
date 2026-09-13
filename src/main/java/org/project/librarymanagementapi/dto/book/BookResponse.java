package org.project.librarymanagementapi.dto.book;

import java.math.BigDecimal;

public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private BigDecimal price;
    private int publicationYear;
    private Long authorId;
    private String authorName;

    public BookResponse(Long id, String title, String isbn, BigDecimal price, int publicationYear, Long authorId, String authorName) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.publicationYear = publicationYear;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }
}
