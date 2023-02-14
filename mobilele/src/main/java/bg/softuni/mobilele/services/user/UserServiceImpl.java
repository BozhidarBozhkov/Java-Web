package bg.softuni.mobilele.services.user;

import bg.softuni.mobilele.domain.beans.LoggedUser;
import bg.softuni.mobilele.domain.dto.binding.UserLoginFormDto;
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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, DataInitializerService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
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

        final User userToSave = this.modelMapper.map(userModel, User.class);

        return this.modelMapper.map(this.userRepository.saveAndFlush(userToSave), UserModel.class);
    }

    @Override
    public UserModel loginUser(UserLoginFormDto userLogin) {
        Optional<User> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        UserModel userConfirmation = loginCandidate.isPresent() && loginCandidate.get().getPassword().equals(userLogin.getPassword())
                ? this.modelMapper.map(loginCandidate.get(), UserModel.class)
                : new UserModel();

        if (userConfirmation.isValid()) {
            this.loggedUser
                    .setId(userConfirmation.getId())
                    .setUsername(userConfirmation.getUsername())
                    .setRoleModels(userConfirmation.getRole());
        }
        return userConfirmation;
    }

    @Override
    public void logout(){
        this.loggedUser.clearFields();
    }
}
