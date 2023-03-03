package com.likebookapp.controller;

import com.likebookapp.model.dto.RegistrationDTO;
import com.likebookapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HomeController {

    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @ModelAttribute("registrationDTO")
    public RegistrationDTO initRegistrationDTO() {
        return new RegistrationDTO();
    }

    @PostMapping("/register")
    public String postRegister(@Valid RegistrationDTO registrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.register(registrationDTO)){
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";

    }
}
