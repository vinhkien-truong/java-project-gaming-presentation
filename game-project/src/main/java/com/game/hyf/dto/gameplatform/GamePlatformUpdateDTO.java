package com.game.hyf.dto.gameplatform;

import com.game.hyf.model.GameFormat;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamePlatformUpdateDTO {  
        @Positive(message = "Price must be positive")
        Double price;
}
