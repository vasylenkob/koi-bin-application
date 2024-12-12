package com.vasylenkob.pastebin.controllers;

import com.vasylenkob.pastebin.exceptions.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(VerificationCodeExpiredException.class)
    public String handleCodeExpired(VerificationCodeExpiredException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(InvalidVerificationCodeException.class)
    public String handleInvalidCode(InvalidVerificationCodeException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(UserAlreadyVerifiedException.class)
    public String handleUserAlreadyVerified(UserAlreadyVerifiedException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(FailedToSendEmailException.class)
    public String handleFailedToSendEmail(FailedToSendEmailException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(PostDoesNotExistException.class)
    public String handlePostDoesNotExist(PostDoesNotExistException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExist(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
