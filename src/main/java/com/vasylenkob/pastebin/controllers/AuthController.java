package com.vasylenkob.pastebin.controllers;

import com.vasylenkob.pastebin.models.SignUpForm;
import com.vasylenkob.pastebin.models.VerifyUserForm;
import com.vasylenkob.pastebin.services.SignupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final SignupService signupService;

    @PostMapping("/signup")
    public String signUp(
            @ModelAttribute("signUpForm") SignUpForm signUpForm,
            RedirectAttributes redirectAttributes
    ) {
        signupService.signUp(signUpForm);
        redirectAttributes.addFlashAttribute("email", signUpForm.getEmail());
        return "redirect:/auth/verify";
    }

    @PostMapping("/verify")
    public String verify(@ModelAttribute VerifyUserForm verifyUserForm) {
        signupService.verify(verifyUserForm);
        return "redirect:/";
    }

    @PostMapping("/resend")
    public String resendEmail(@RequestParam String email, RedirectAttributes redirectAttributes) {
        if (email == null || email.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Sign up first!");
            return "redirect:/auth/verify";
        }

        try {
            signupService.resendEmail(email);
            redirectAttributes.addFlashAttribute("message", "Verification code resent successfully!");
            redirectAttributes.addFlashAttribute("email", email);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to resend code. Please try again.");
        }

        return "redirect:/auth/verify";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password! Please try again.");
            return "error";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute SignUpForm signUpForm) {
        return "signup";
    }

    @GetMapping("/verify")
    public String verifyUser(VerifyUserForm verifyUserForm, @ModelAttribute("email") String email, Model model) {
        if (email != null) {
            verifyUserForm.setEmail(email);
        }

        model.addAttribute("verifyUserForm", verifyUserForm);

        return "verify";
    }
}