package kz.example.lms.model;

public class User {
    private final int id;
    private final String fullName;
    private final String email;
    private final String role;
    private final String avatarUrl;

    public User(int id, String fullName, String email, String role, String avatarUrl) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
