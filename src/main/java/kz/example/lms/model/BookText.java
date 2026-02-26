package kz.example.lms.model;

public class BookText {
    private final int bookId;
    private final String language;
    private final String description;
    private final String resourcePath;

    public BookText(int bookId, String language, String description, String resourcePath) {
        this.bookId = bookId;
        this.language = language;
        this.description = description;
        this.resourcePath = resourcePath;
    }

    public int getBookId() {
        return bookId;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
