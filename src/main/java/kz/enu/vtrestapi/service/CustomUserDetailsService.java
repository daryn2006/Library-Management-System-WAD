package kz.enu.vtrestapi.service;

import kz.enu.vtrestapi.model.AppUser;
import kz.enu.vtrestapi.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    public CustomUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lookup = username == null ? "" : username.trim();
        if (!StringUtils.hasText(lookup)) {
            throw new UsernameNotFoundException("Username is required");
        }

        AppUser user = repository.findByEmail(lookup)
                .or(() -> repository.findByUsername(lookup))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + lookup));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
