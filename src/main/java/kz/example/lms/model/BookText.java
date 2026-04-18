package kz.example.lms.model;

public class BookText {
    private final int bookId;
    private final String language;
    private final String description;
    private final String resourcePath;
    private final String sourceUrl;

    public BookText(int bookId, String language, String description, String resourcePath, String sourceUrl) {
        this.bookId = bookId;
        this.language = language;
        this.description = description;
        this.resourcePath = resourcePath;
        this.sourceUrl = sourceUrl;
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

    public String getSourceUrl() {
        return sourceUrl;
    }
}
