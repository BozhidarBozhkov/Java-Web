package bg.softuni.mobilele.services.userRole;

import bg.softuni.mobilele.domain.dto.model.UserRoleModel;
import bg.softuni.mobilele.domain.dto.view.UserRoleViewDto;
import bg.softuni.mobilele.domain.entities.UserRole;
import bg.softuni.mobilele.domain.enums.Role;
import bg.softuni.mobilele.repositories.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.dbInit();
    }


    @Override
    public void dbInit() {
        if (!isDBInit()) {
            List<UserRole> roles = new ArrayList<>();

            roles.add(new UserRole().setRole(Role.USER));
            roles.add(new UserRole().setRole(Role.ADMIN));

            this.userRoleRepository.saveAllAndFlush(roles);
        }

    }

    @Override
    public boolean isDBInit() {
        return this.userRoleRepository.count() > 0;
    }

    public List<UserRoleViewDto> getAll() {
        return this.userRoleRepository.findAll().stream()
                .map(role -> this.modelMapper.map(role, UserRoleViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRoleModel> findAllRoles() {
        return this.userRoleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserRoleModel findRoleByName(String name) {
        return this.modelMapper.map(this.userRoleRepository.findByRole(Role.valueOf(name))
                        .orElseThrow(NoSuchElementException::new),
                UserRoleModel.class);
    }
}
