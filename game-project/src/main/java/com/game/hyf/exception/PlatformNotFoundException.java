package com.game.hyf.exception;

import java.util.UUID;

public class PlatformNotFoundException extends RuntimeException {
    public PlatformNotFoundException(UUID id) {
        super("platform with id " + id + " not found");
    }
    
}
