package bg.softuni.mobilele.services.model;

import bg.softuni.mobilele.repositories.ModelRepository;
import bg.softuni.mobilele.services.init.DataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService, DataInitializerService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDBInit() {
        return this.modelRepository.count() > 0;
    }
}
