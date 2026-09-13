package org.project.librarymanagementapi.dto.book;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class BookRequest {

    @NotEmpty(message = "Name cannot be empty")
    private String title;

    @NotNull(message = "IsBn cannot be null")
    private String isbn;

    @DecimalMin(value = "0", message = "Price cannot below 0")
    @Positive(message = "Price cannot be negative")
    private BigDecimal price;

    @Positive
    private int publicationYear;

    @NotNull(message = "Author cannot be null")
    private Long authorId;

    public BookRequest(String title, String isbn, BigDecimal price, int publicationYear, Long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.publicationYear = publicationYear;
        this.authorId = authorId;
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
}
