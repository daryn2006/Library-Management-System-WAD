package kz.enu.vtrestapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.enu.vtrestapi.model.AppUser;
import kz.enu.vtrestapi.service.UserAccountService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CurrentUserAttributesFilter extends OncePerRequestFilter {

    private final UserAccountService userAccountService;

    public CurrentUserAttributesFilter(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            AppUser user = userAccountService.findByUsername(authentication.getName()).orElse(null);
            if (user != null) {
                if (StringUtils.hasText(user.getAvatarUrl())) {
                    request.setAttribute("currentAvatarUrl", user.getAvatarUrl());
                }
                String displayName = StringUtils.hasText(user.getNickname())
                        ? user.getNickname().trim()
                        : StringUtils.hasText(user.getFullName())
                        ? user.getFullName().trim()
                        : user.getUsername();
                request.setAttribute("currentDisplayName", displayName);
            }
        }

        filterChain.doFilter(request, response);
    }
}
