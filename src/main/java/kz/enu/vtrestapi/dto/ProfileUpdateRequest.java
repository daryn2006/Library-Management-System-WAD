package kz.enu.vtrestapi.dto;

import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ProfileUpdateRequest {

    @Size(max = 255, message = "Full name must be up to 255 characters")
    private String fullName;

    @Size(max = 120, message = "Nickname must be up to 120 characters")
    private String nickname;

    @Size(max = 500, message = "Avatar URL must be up to 500 characters")
    private String avatarUrl;

    private MultipartFile avatarFile;

    @Size(max = 100, message = "Password must be up to 100 characters")
    private String currentPassword;

    @Size(max = 100, message = "Password must be up to 100 characters")
    private String newPassword;

    @Size(max = 100, message = "Password must be up to 100 characters")
    private String confirmPassword;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
