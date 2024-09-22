package com.example.megachat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDto {

    @NotBlank
    @Pattern(regexp = ".{2,255}")
    private String username;

    @NotBlank
    @Pattern(regexp = ".{3,255}")
    private String password;

    @Pattern(regexp = ".{0,255}")
    private String name;
}
