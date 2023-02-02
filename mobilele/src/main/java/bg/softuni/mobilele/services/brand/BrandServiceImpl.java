package bg.softuni.mobilele.services.brand;

import bg.softuni.mobilele.repositories.BrandRepository;
import bg.softuni.mobilele.services.init.DataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService, DataInitializerService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDBInit() {
        return this.brandRepository.count() > 0;
    }
}
