package com.game.hyf.dto.platform;

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
public class PlatformCreateDTO {
        @NotBlank(message = "Platform name is required")
        @Size(max = 100)
        String name;

        @NotBlank(message = "Manufacturer is required")
        @Size(max = 100)
        String manufacturer;
}
