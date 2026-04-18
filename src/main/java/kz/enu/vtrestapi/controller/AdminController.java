package kz.enu.vtrestapi.controller;

import kz.enu.vtrestapi.dto.AdminUserForm;
import kz.enu.vtrestapi.exception.BusinessRuleException;
import kz.enu.vtrestapi.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserAccountService userAccountService;

    public AdminController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userAccountService.findAllUsers());
        return "admin/users";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        AdminUserForm form = new AdminUserForm();
        form.setRole("USER");
        model.addAttribute("userForm", form);
        return "admin/create-user";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("userForm") AdminUserForm form,
                             BindingResult bindingResult,
                             Model model) {
        if (!hasText(form.getPassword())) {
            bindingResult.rejectValue("password", "required", "Password is required");
        }
        if (bindingResult.hasErrors()) {
            return "admin/create-user";
        }

        try {
            userAccountService.createAdminUser(form);
        } catch (BusinessRuleException ex) {
            if (ex.getField() != null) {
                bindingResult.rejectValue(ex.getField(), "businessRule", ex.getMessage());
            } else {
                model.addAttribute("error", ex.getMessage());
            }
            return "admin/create-user";
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        userAccountService.findUserById(id).ifPresentOrElse(user -> {
            AdminUserForm form = new AdminUserForm();
            form.setId(user.getId());
            form.setUsername(user.getUsername());
            form.setRole(user.getRole());
            model.addAttribute("userForm", form);
        }, () -> model.addAttribute("error", "User not found"));
        if (!model.containsAttribute("userForm")) {
            return "redirect:/admin/users";
        }
        return "admin/edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute("userForm") AdminUserForm form,
                           BindingResult bindingResult,
                           Model model) {
        if (form.getId() == null) {
            return "redirect:/admin/users";
        }

        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }

        try {
            userAccountService.updateAdminUser(form);
        } catch (BusinessRuleException ex) {
            if (ex.getField() != null) {
                bindingResult.rejectValue(ex.getField(), "businessRule", ex.getMessage());
            } else {
                model.addAttribute("error", ex.getMessage());
            }
            return "admin/edit-user";
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            userAccountService.deleteUser(id);
        } catch (BusinessRuleException ex) {
            return "redirect:/admin/users";
        }
        return "redirect:/admin/users";
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
