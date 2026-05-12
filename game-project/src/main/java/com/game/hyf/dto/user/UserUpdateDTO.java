package com.game.hyf.dto.user;

import com.game.hyf.model.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {
    @Size(max = 50)
    String username;

    @Email(message = "Invalid email")
    String email;

    String password;

    UserRole role;

    String country;
}
