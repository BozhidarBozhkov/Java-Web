package com.resellerapp.config;

import com.resellerapp.model.helpers.LoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public LoggedUser loggedUser() {
        return new LoggedUser();
    }
}
