package kz.enu.vtrestapi.service;

import kz.enu.vtrestapi.dto.AdminUserForm;
import kz.enu.vtrestapi.dto.RegisterRequest;
import kz.enu.vtrestapi.dto.ProfileUpdateRequest;
import kz.enu.vtrestapi.dto.UserSummaryResponse;
import kz.enu.vtrestapi.exception.BusinessRuleException;
import kz.enu.vtrestapi.model.AppUser;
import kz.enu.vtrestapi.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class UserAccountService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser register(RegisterRequest request) {
        String email = normalizeEmail(request.getEmail());
        ensureUniqueEmail(email, null);

        AppUser user = new AppUser();
        user.setFullName(trim(request.getFullName()));
        user.setNickname(resolveNickname(request.getFullName(), email));
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        return repository.save(user);
    }

    public AppUser createAdminUser(AdminUserForm form) {
        String username = normalizeUsername(form.getUsername());
        ensureUniqueUsername(username, null);
        ensureRole(form.getRole());
        ensurePasswordProvided(form.getPassword());

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setEmail(username);
        user.setFullName(username);
        user.setNickname(username);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(form.getRole().trim().toUpperCase(Locale.ROOT));
        return repository.save(user);
    }

    public AppUser updateAdminUser(AdminUserForm form) {
        AppUser user = repository.findById(form.getId())
                .orElseThrow(() -> new BusinessRuleException("id", "User not found"));

        String username = normalizeUsername(form.getUsername());
        ensureUniqueUsername(username, user.getId());
        ensureRole(form.getRole());

        user.setUsername(username);
        user.setEmail(username);
        user.setFullName(username);
        user.setNickname(username);
        user.setRole(form.getRole().trim().toUpperCase(Locale.ROOT));
        if (StringUtils.hasText(form.getPassword())) {
            ensurePasswordProvided(form.getPassword());
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        }
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public List<AppUser> findAllUsers() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AppUser> findUserById(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<UserSummaryResponse> findAllUserSummaries() {
        return repository.findAll()
                .stream()
                .map(user -> new UserSummaryResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getRole()))
                .toList();
    }

    public void deleteUser(Long id) {
        if (id == null || !repository.existsById(id)) {
            throw new BusinessRuleException("id", "User not found");
        }
        repository.deleteById(id);
    }

    public AppUser updateProfile(String username, ProfileUpdateRequest request) {
        AppUser user = repository.findByUsername(username)
                .orElseThrow(() -> new BusinessRuleException("username", "User not found"));

        if (StringUtils.hasText(request.getFullName())) {
            user.setFullName(trim(request.getFullName()));
        }
        if (request.getNickname() != null) {
            user.setNickname(StringUtils.hasText(request.getNickname()) ? trim(request.getNickname()) : null);
        }
        if (request.getAvatarFile() != null && !request.getAvatarFile().isEmpty()) {
            user.setAvatarUrl(toDataUrl(request.getAvatarFile()));
        } else if (StringUtils.hasText(request.getAvatarUrl())) {
            user.setAvatarUrl(trim(request.getAvatarUrl()));
        } else if (request.getAvatarUrl() != null) {
            user.setAvatarUrl(null);
        }

        boolean passwordChangeRequested = StringUtils.hasText(request.getNewPassword())
                || StringUtils.hasText(request.getConfirmPassword())
                || StringUtils.hasText(request.getCurrentPassword());
        if (passwordChangeRequested) {
            if (!StringUtils.hasText(request.getCurrentPassword())) {
                throw new BusinessRuleException("currentPassword", "Current password is required");
            }
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new BusinessRuleException("currentPassword", "Current password is incorrect");
            }
            if (!StringUtils.hasText(request.getNewPassword())) {
                throw new BusinessRuleException("newPassword", "New password is required");
            }
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                throw new BusinessRuleException("confirmPassword", "Passwords do not match");
            }
            ensurePasswordProvided(request.getNewPassword());
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<AppUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    private void ensureRole(String role) {
        String normalized = trim(role).toUpperCase(Locale.ROOT);
        if (!"USER".equals(normalized) && !"ADMIN".equals(normalized)) {
            throw new BusinessRuleException("role", "Role must be USER or ADMIN");
        }
    }

    private void ensureUniqueEmail(String email, Long currentId) {
        repository.findByEmail(email)
                .filter(user -> currentId == null || !currentId.equals(user.getId()))
                .ifPresent(user -> {
                    throw new BusinessRuleException("email", "Email already exists");
                });
        repository.findByUsername(email)
                .filter(user -> currentId == null || !currentId.equals(user.getId()))
                .ifPresent(user -> {
                    throw new BusinessRuleException("email", "Email already exists");
                });
    }

    private void ensureUniqueUsername(String username, Long currentId) {
        repository.findByUsername(username)
                .filter(user -> currentId == null || !currentId.equals(user.getId()))
                .ifPresent(user -> {
                    throw new BusinessRuleException("username", "Username already exists");
                });
        repository.findByEmail(username)
                .filter(user -> currentId == null || !currentId.equals(user.getId()))
                .ifPresent(user -> {
                    throw new BusinessRuleException("username", "Username already exists");
                });
    }

    private void ensurePasswordProvided(String password) {
        if (!StringUtils.hasText(password) || password.trim().length() < 6) {
            throw new BusinessRuleException("password", "Password must be at least 6 characters");
        }
    }

    private String normalizeEmail(String email) {
        String value = trim(email).toLowerCase(Locale.ROOT);
        if (!StringUtils.hasText(value)) {
            throw new BusinessRuleException("email", "Email is required");
        }
        return value;
    }

    private String normalizeUsername(String username) {
        String value = trim(username);
        if (!StringUtils.hasText(value)) {
            throw new BusinessRuleException("username", "Username is required");
        }
        return value;
    }

    private String resolveNickname(String nickname, String fallback) {
        String value = trim(nickname);
        return StringUtils.hasText(value) ? value : trim(fallback);
    }

    private String toDataUrl(MultipartFile file) {
        String contentType = file.getContentType();
        if (!StringUtils.hasText(contentType) || !contentType.startsWith("image/")) {
            throw new BusinessRuleException("avatarFile", "Avatar must be an image");
        }
        try {
            String encoded = Base64.getEncoder().encodeToString(file.getBytes());
            return "data:" + contentType + ";base64," + encoded;
        } catch (IOException ex) {
            throw new BusinessRuleException("avatarFile", "Unable to read avatar file");
        }
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
