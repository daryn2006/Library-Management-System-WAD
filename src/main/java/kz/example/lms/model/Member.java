package kz.example.lms.model;

public class Member {
    private final int id;
    private final String fullName;
    private final String email;
    private final String phone;
    private final int libraryId;
    private final String libraryName;

    public Member(int id, String fullName, String email, String phone, int libraryId, String libraryName) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.libraryId = libraryId;
        this.libraryName = libraryName;
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

    public String getPhone() {
        return phone;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }
}
