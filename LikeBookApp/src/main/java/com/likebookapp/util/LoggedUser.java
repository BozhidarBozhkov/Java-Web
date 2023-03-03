package com.likebookapp.util;

import com.likebookapp.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private Long id;
    private String username;

    public LoggedUser() {}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void login(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout(UserEntity user) {
        this.id = null;
        this.username = null;
    }
}
