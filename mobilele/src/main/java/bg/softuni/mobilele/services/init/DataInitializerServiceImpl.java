package bg.softuni.mobilele.services.init;

import bg.softuni.mobilele.services.userRole.UserRoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInitializerServiceImpl implements DataInitializerService {
    private final UserRoleService roleService;

    @Autowired
    public DataInitializerServiceImpl(UserRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void dbInit() {
        if (isDBInit()) {
            this.roleService.dbInit();
        }

    }

    @PostConstruct
    public void postConstruct() {
        dbInit();
    }

    @Override
    public boolean isDBInit() {
        return this.roleService.isDBInit();
    }

}
