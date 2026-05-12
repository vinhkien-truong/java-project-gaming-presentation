package com.game.hyf.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("user with id " + id + " not found");
    }
    
}
