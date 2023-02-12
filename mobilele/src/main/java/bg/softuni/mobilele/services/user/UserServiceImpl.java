package bg.softuni.mobilele.services.user;

import bg.softuni.mobilele.domain.dto.binding.UserRegisterFormDto;
import bg.softuni.mobilele.domain.dto.model.UserModel;
import bg.softuni.mobilele.domain.entities.User;
import bg.softuni.mobilele.repositories.UserRepository;
import bg.softuni.mobilele.services.init.DataInitializerService;
import bg.softuni.mobilele.services.userRole.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, DataInitializerService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserService userService, UserRoleService userRoleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDBInit() {
        return this.userRepository.count() > 0;
    }

    @Override
    public UserModel registerUser(UserRegisterFormDto userRegister) {
        final UserModel userModel = this.modelMapper.map(userRegister, UserModel.class);

        userModel.setRole(this.userRepository.count() == 0
                ? this.userRoleService.findAllRoles()
                : List.of(this.userRoleService.findRoleByName("USER")));

        User userToSave = this.modelMapper.map(userModel, User.class);

        return this.modelMapper.map(this.userRepository.saveAndFlush(userToSave), UserModel.class);
    }

    public void loginUser() {

    }
}
