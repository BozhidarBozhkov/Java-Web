package com.likebookapp.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDTO {


    @Size(min = 3, max = 20)
    @NotBlank(message = "Username length must be between 3 and 20 characters!")
    private String username;


    @Size(min = 3, max = 20)
    @NotBlank(message = "Password length must be between 3 and 20 characters!")
    private String password;

    public LoginDTO() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
