package kz.example.lms.model;

public class Author {
    private final int id;
    private final String fullName;
    private final String country;

    public Author(int id, String fullName, String country) {
        this.id = id;
        this.fullName = fullName;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCountry() {
        return country;
    }
}
