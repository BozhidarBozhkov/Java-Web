package bg.softuni.mobilele.services.userRole;

import bg.softuni.mobilele.repositories.UserRoleRepository;
import bg.softuni.mobilele.services.init.DataInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService, DataInitializerService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDBInit() {
        return this.userRoleRepository.count() > 0;
    }
}
