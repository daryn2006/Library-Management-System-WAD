package kz.enu.vtrestapi.controller;

import kz.enu.vtrestapi.dto.RegisterRequest;
import kz.enu.vtrestapi.exception.BusinessRuleException;
import kz.enu.vtrestapi.model.AppUser;
import kz.enu.vtrestapi.service.EmailService;
import kz.enu.vtrestapi.service.UserAccountService;
import kz.example.lms.service.LmsDataService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserAccountService userAccountService;
    private final EmailService emailService;
    private final LmsDataService lmsDataService;

    public AuthController(UserAccountService userAccountService, EmailService emailService, LmsDataService lmsDataService) {
        this.userAccountService = userAccountService;
        this.emailService = emailService;
        this.lmsDataService = lmsDataService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        if (!model.containsAttribute("registerRequest")) {
            model.addAttribute("registerRequest", new RegisterRequest());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest request,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            AppUser savedUser = userAccountService.register(request);
            try {
                if (lmsDataService.findMemberByEmail(savedUser.getEmail()) == null) {
                    lmsDataService.createMember(savedUser.getFullName(), savedUser.getEmail(), null, 1);
                }
            } catch (Exception ex) {
                log.warn("Failed to sync new reader {}", savedUser.getEmail(), ex);
            }
            try {
                emailService.sendEmail(
                        savedUser.getEmail(),
                        "Registration successful",
                        "Hello " + savedUser.getFullName() + ", your account has been created successfully."
                );
            } catch (Exception ex) {
                log.warn("Failed to send registration email to {}", savedUser.getEmail(), ex);
            }
        } catch (BusinessRuleException ex) {
            if (ex.getField() != null) {
                bindingResult.rejectValue(ex.getField(), "businessRule", ex.getMessage());
            } else {
                bindingResult.reject("businessRule", ex.getMessage());
            }
            return "register";
        }

        return "redirect:/login?registered";
    }
}
