package com.likebookapp.controller;

import com.likebookapp.model.dto.LoginDTO;
import com.likebookapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDto() {
        return new LoginDTO();
    }

    //TODO check this method
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("badCredentials");
    }

    @PostMapping("/login")
    public String postLogin(@Valid LoginDTO loginDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.authService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";

        }
        return "redirect:/home";
    }
}
