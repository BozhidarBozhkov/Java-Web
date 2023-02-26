package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionType;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.helpers.LoggedUser;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ConditionRepository conditionRepository;

    public OfferService(OfferRepository offerRepository, LoggedUser loggedUser, UserRepository userRepository, ConditionRepository conditionRepository) {
        this.offerRepository = offerRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
    }

    public void offerAdd(AddOfferDTO offerDTO) {

        Optional<User> currentUser = this.userRepository.findById(loggedUser.getId());

        ConditionType offerCondition = ConditionType.valueOf(offerDTO.getCondition());
        Condition condition = this.conditionRepository.findByName(offerCondition);

        Offer offer = new Offer();
        offer.setDescription(offerDTO.getDescription());
        offer.setPrice(offerDTO.getPrice());
        offer.setCondition(condition);
        offer.setOwner(currentUser.get());

        this.offerRepository.save(offer);

    }
}
