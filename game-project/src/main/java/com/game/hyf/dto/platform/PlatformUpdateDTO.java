package com.game.hyf.dto.platform;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlatformUpdateDTO {
    @Size(max = 100)
    String name;

    @Size(max = 100)
    String manufacturer;
}
