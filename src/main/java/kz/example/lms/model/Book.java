package kz.example.lms.model;

public class Book {
    private final int id;
    private final String title;
    private final String genre;
    private final String isbn;
    private final int authorId;
    private final int libraryId;
    private final Integer publishedYear;
    private final String authorName;
    private final String libraryName;

    public Book(int id, String title, String genre, String isbn, int authorId, int libraryId, Integer publishedYear,
                String authorName, String libraryName) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
        this.authorId = authorId;
        this.libraryId = libraryId;
        this.publishedYear = publishedYear;
        this.authorName = authorName;
        this.libraryName = libraryName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getLibraryName() {
        return libraryName;
    }
}
