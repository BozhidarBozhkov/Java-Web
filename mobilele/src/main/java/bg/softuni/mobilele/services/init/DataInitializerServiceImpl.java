package bg.softuni.mobilele.services.init;

import org.springframework.stereotype.Service;

@Service
public class DataInitializerServiceImpl implements DataInitializerService {

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDBInit() {
        return true;
    }

}
