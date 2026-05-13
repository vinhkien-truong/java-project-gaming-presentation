package com.game.hyf.dto.user;

import com.game.hyf.model.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDTO {

    
    @NotBlank(message = "Username is required")
    @Size(max = 50)
    String username;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Password is required")
    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    @NotBlank(message = "Country is required")
    String country;
}
