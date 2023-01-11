package com.byms.mservice.service;


import com.byms.mservice.model.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MService {

    public User getWelcomeMessage(@NonNull String name) {

        User user = new User();
        user.setUsername(name);
        user.setId(UUID.randomUUID());

        return user;
    }
}
