package bg.softuni.mobilele.services.user;

import bg.softuni.mobilele.domain.dto.binding.UserRegisterFormDto;
import bg.softuni.mobilele.domain.dto.model.UserModel;

public interface UserService {
    UserModel registerUser(UserRegisterFormDto userRegister);
}
