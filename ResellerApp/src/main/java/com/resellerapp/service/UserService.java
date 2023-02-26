package com.resellerapp.service;

import com.resellerapp.model.dto.BoughtItemsDTO;
import com.resellerapp.model.helpers.LoggedUser;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;


    public UserService(LoggedUser loggedUser, UserRepository userRepository, OfferRepository offerRepository) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    public List<BoughtItemsDTO> boughtItemsDTOList() {
        return this.userRepository.findById(loggedUser.getId()).get()
                .getBoughtOffers().stream()
                .map(BoughtItemsDTO::new).collect(Collectors.toList());
    }
}
