package bg.softuni.mobilele.services.userRole;

import bg.softuni.mobilele.domain.dto.view.UserRoleViewDto;
import bg.softuni.mobilele.domain.entities.UserRole;
import bg.softuni.mobilele.domain.enums.Role;
import bg.softuni.mobilele.repositories.UserRoleRepository;
import bg.softuni.mobilele.services.init.DataInitializerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService, DataInitializerService {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void dbInit() {
        List<UserRole> roles = new ArrayList<>();

        roles.add(new UserRole().setRole(Role.USER));
        roles.add(new UserRole().setRole(Role.ADMIN));

        this.userRoleRepository.saveAllAndFlush(roles);
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
}
