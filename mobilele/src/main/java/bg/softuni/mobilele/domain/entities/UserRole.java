package bg.softuni.mobilele.domain.entities;

import bg.softuni.mobilele.domain.enums.Role;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Role name;

    public UserRole() {
    }

    public Role getRole() {
        return name;
    }

    public UserRole setRole(Role name) {
        this.name = name;
        return this;
    }
}
