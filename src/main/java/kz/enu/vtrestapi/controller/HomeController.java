package kz.enu.vtrestapi.controller;

import kz.enu.vtrestapi.dto.ProfileUpdateRequest;
import kz.enu.vtrestapi.exception.BusinessRuleException;
import kz.enu.vtrestapi.model.AppUser;
import kz.enu.vtrestapi.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.util.StringUtils;

@Controller
public class HomeController {

    private final UserAccountService userAccountService;

    public HomeController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "home";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication auth) {
        AppUser user = userAccountService.findByUsername(auth.getName()).orElse(null);
        model.addAttribute("username", auth.getName());
        model.addAttribute("profile", user);
        model.addAttribute("displayName", resolveDisplayName(user, auth.getName()));
        if (!model.containsAttribute("profileForm")) {
            ProfileUpdateRequest form = new ProfileUpdateRequest();
            if (user != null) {
                form.setFullName(user.getFullName());
                form.setNickname(user.getNickname());
                form.setAvatarUrl(user.getAvatarUrl());
            }
            model.addAttribute("profileForm", form);
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Authentication auth,
                                @Valid @ModelAttribute("profileForm") ProfileUpdateRequest form,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return profile(model, auth);
        }

        try {
            userAccountService.updateProfile(auth.getName(), form);
        } catch (BusinessRuleException ex) {
            if (ex.getField() != null) {
                bindingResult.rejectValue(ex.getField(), "businessRule", ex.getMessage());
            } else {
                model.addAttribute("error", ex.getMessage());
            }
            return profile(model, auth);
        }

        return "redirect:/profile?updated";
    }

    @GetMapping("/lab11")
    public String lab11(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        return "lab11";
    }

    private String resolveDisplayName(AppUser user, String fallback) {
        if (user == null) {
            return fallback;
        }
        if (StringUtils.hasText(user.getNickname())) {
            return user.getNickname().trim();
        }
        if (StringUtils.hasText(user.getFullName())) {
            return user.getFullName().trim();
        }
        return fallback;
    }
}
