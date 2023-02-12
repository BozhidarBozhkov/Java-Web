package bg.softuni.mobilele.services.userRole;

import bg.softuni.mobilele.domain.dto.model.UserRoleModel;
import bg.softuni.mobilele.domain.dto.view.UserRoleViewDto;
import bg.softuni.mobilele.services.init.DataInitializerService;

import java.util.List;

public interface UserRoleService extends DataInitializerService {
    List<UserRoleViewDto> getAll();

    List<UserRoleModel> findAllRoles();

    UserRoleModel findRoleByName(String name);
}
