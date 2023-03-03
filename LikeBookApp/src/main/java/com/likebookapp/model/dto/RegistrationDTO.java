package com.likebookapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegistrationDTO {

    @NotBlank
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Enter valid email!")
    private String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String confirmPassword;

    public RegistrationDTO() {}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public RegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public RegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
