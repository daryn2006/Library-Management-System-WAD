package kz.enu.vtrestapi.controller;

import kz.enu.vtrestapi.dto.UserSummaryResponse;
import kz.enu.vtrestapi.service.UserAccountService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserAccountService userAccountService;

    public UserRestController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("username", authentication.getName());
        response.put("authenticated", authentication.isAuthenticated());
        return response;
    }

    @GetMapping
    public List<UserSummaryResponse> allUsers() {
        return userAccountService.findAllUserSummaries();
    }
}
