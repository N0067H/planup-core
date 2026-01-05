package com.example.planupcore.domain.user.exception;


import com.example.planupcore.global.exception.NotFoundException;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(UUID id) {
        super("User not found: " + id);
    }
}

