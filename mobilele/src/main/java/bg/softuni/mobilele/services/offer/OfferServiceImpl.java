package bg.softuni.mobilele.services.offer;

import bg.softuni.mobilele.repositories.OfferRepository;
import bg.softuni.mobilele.services.init.DataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService, DataInitializerService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDBInit() {
        return this.offerRepository.count() > 0;
    }
}
