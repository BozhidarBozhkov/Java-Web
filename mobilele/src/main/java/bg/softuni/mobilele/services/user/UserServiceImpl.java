package bg.softuni.mobilele.services.user;

import bg.softuni.mobilele.repositories.UserRepository;
import bg.softuni.mobilele.services.init.DataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, DataInitializerService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDBInit() {
        return this.userRepository.count() > 0;
    }
}
