package bg.softuni.mobilele.domain.dto.view;

import bg.softuni.mobilele.domain.enums.Role;

public class UserRoleViewDto {

    private Role role;

    public Role getRole() {
        return role;
    }

    public UserRoleViewDto setRole(Role role) {
        this.role = role;
        return this;
    }
}
