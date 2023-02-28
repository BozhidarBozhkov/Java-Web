package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.dto.BoughtItemsDTO;
import com.resellerapp.model.dto.OtherUserOfferDto;
import com.resellerapp.model.dto.UserWithOfferDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionType;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.helpers.LoggedUser;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserWithOfferDTO> userWithOfferDTOList() {
        return this.offerRepository.findAllByOwnerId(loggedUser.getId())
                .stream().map(UserWithOfferDTO::new).collect(Collectors.toList());
    }

    public List<OtherUserOfferDto> otherUserOfferDtoList() {
        return this.offerRepository.findAllByOwnerIdNot(loggedUser.getId())
                .stream().map(OtherUserOfferDto::new).collect(Collectors.toList());
    }

    public void buyOffer(Long offerId, Long ownerId) {
        Offer offerToBuy = this.offerRepository.findById(offerId).get();
        offerToBuy.setOwner(null);

        User user = this.userRepository.findById(ownerId).get();
        offerToBuy.setOwner(user);
        user.getBoughtOffers().add(offerToBuy);

        this.offerRepository.save(offerToBuy);
        this.userRepository.save(user);
    }

    public void removeOffer(Long offerId) {
        this.offerRepository.deleteById(offerId);
    }

}
