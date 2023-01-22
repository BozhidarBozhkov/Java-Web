package bg.softuni.mobilele.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRole() {
    }

    public UserRole getRole() {
        return role;
    }

    public UserRole setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
