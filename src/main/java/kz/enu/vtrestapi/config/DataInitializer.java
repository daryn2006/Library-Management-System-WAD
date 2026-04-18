package kz.enu.vtrestapi.config;

import kz.enu.vtrestapi.model.AppUser;
import kz.enu.vtrestapi.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        AppUser admin = repository.findByUsername("admin").orElseGet(AppUser::new);
        admin.setUsername("admin");
        admin.setEmail("admin@example.com");
        admin.setFullName("Admin");
        admin.setNickname("Admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole("ADMIN");
        admin.setAvatarUrl("https://ui-avatars.com/api/?name=Admin&background=0f766e&color=fff");
        repository.save(admin);
    }
}
