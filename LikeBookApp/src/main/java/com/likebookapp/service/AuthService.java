package com.likebookapp.service;

import com.likebookapp.model.dto.LoginDTO;
import com.likebookapp.model.dto.RegistrationDTO;
import com.likebookapp.model.entity.UserEntity;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public boolean register(RegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            return false;
        }

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
        if (byEmail.isPresent()){
            return false;
        }

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());
        if (byUsername.isPresent()){
            return false;
        }

        UserEntity user = new UserEntity();
        user.setEmail(registrationDTO.getEmail());
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(loginDTO.getUsername());

        if (byUsername.isPresent()){
            String password = byUsername.get().getPassword();
            String loginDTOPassword = loginDTO.getPassword();

            if (passwordEncoder.matches(password, loginDTOPassword)){
                this.loggedUser.login(byUsername.get());

                return true;
            }
            return false;
        }
        return false;
    }
}
