package com.game.hyf.exception;

import java.util.UUID;

public class GamePlatformNotFoundException  extends RuntimeException {
    public GamePlatformNotFoundException(UUID id) {
        super("platform with id " + id + " not found");
    }
    
}
