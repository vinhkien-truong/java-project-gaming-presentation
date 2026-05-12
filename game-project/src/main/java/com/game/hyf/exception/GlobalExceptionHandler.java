package com.game.hyf.exception;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GameNotFoundException.class)
    public Map<String,String> handleGameNotFound(GameNotFoundException ex)
    {
        return Map.of("error", ex.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlatformNotFoundException.class)
    public Map<String,String> handlePlatformNotFound(PlatformNotFoundException ex)
    {
        return Map.of("error", ex.getMessage());
    }   
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GamePlatformNotFoundException.class)
    public Map<String,String> handleGamePlatformNotFound(GamePlatformNotFoundException ex)
    {
        return Map.of("error", ex.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> handleUserNotFound(UserNotFoundException ex)
    {
        return Map.of("error", ex.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException.class)
    public Map<String,String> handleReviewNotFound(ReviewNotFoundException ex)
    {
        return Map.of("error", ex.getMessage());
    }
}
