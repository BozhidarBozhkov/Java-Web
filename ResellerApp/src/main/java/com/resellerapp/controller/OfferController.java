package com.resellerapp.controller;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.helpers.LoggedUser;
import com.resellerapp.service.AuthService;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final AuthService authService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, AuthService authService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.authService = authService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/offer/add")
    public String addOffer() {
        return "offer-add";
    }

    @ModelAttribute("offerDTO")
    public AddOfferDTO initOfferDTO(){
        return new AddOfferDTO();
    }

    @PostMapping("/offer/add")
    public String addOffer(@Valid AddOfferDTO offerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDTO", offerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDTO", bindingResult);

            return "redirect:/offer/add";
        }

        this.offerService.offerAdd(offerDTO);
        return "redirect:/home";

    }
}
