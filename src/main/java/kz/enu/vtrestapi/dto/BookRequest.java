package kz.enu.vtrestapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 120, message = "Title must be <= 120 characters")
    private String title;

    @NotBlank(message = "ISBN is required")
    @Size(max = 30, message = "ISBN must be <= 30 characters")
    private String isbn;

    @NotNull(message = "Author id is required")
    @Min(value = 1, message = "Author id must be >= 1")
    private Integer authorId;

    @NotNull(message = "Library id is required")
    @Min(value = 1, message = "Library id must be >= 1")
    private Integer libraryId;

    @Min(value = 1500, message = "Year must be >= 1500")
    @Max(value = 2100, message = "Year must be <= 2100")
    private Integer publishedYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
