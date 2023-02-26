package com.resellerapp.controller;

import com.resellerapp.model.dto.OtherUserOfferDto;
import com.resellerapp.model.dto.UserRegistrationDTO;
import com.resellerapp.model.dto.UserWithOfferDTO;
import com.resellerapp.service.AuthService;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    private final AuthService authService;
    private final OfferService offerService;

    public HomeController(AuthService authService, OfferService offerService) {
        this.authService = authService;
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegistrationDTO registrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !authService.register(registrationDTO)){
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";

    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        List<UserWithOfferDTO> userWithOfferList = this.offerService.userWithOfferDTOList();
        List<OtherUserOfferDto> otherUserOfferList = this.offerService.otherUserOfferDtoList();

        model.addAttribute("userWithOffers", userWithOfferList);
        model.addAttribute("otherUserOffers", otherUserOfferList);
        return "home";
    }

    @GetMapping("/logout")
    public String loggedOutIndex() {
        this.authService.logout();
        return "index";
    }

}
