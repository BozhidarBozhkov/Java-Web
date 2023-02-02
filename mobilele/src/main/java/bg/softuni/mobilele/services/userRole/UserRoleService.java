package bg.softuni.mobilele.services.userRole;

import bg.softuni.mobilele.domain.dto.view.UserRoleViewDto;

import java.util.List;

public interface UserRoleService {
    List<UserRoleViewDto> getAll();
}
