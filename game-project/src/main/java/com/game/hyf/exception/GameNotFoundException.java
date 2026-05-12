package com.game.hyf.exception;

import java.util.UUID;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(UUID id) {
        super("product with id " + id + " not found");
    }
    
}
