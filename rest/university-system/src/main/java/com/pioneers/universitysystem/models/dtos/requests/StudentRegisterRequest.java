package com.pioneers.universitysystem.models.dtos.requests;

import com.pioneers.universitysystem.validation.Age;
import jakarta.validation.constraints.*;

public record StudentRegisterRequest(
        @NotBlank(message = "{validation.first-name.blank}")
        @Size(min = 2, max = 15, message = "{validation.first-name.size}")
        String firstName,
        @NotBlank(message = "{validation.second-name.blank}")
        @Size(min = 2, max = 15, message = "{validation.second-name.size}")
        String lastName,
        @Age(min = 18, max = 25, message = "{validation.age.error}")
        int age,
        @Email(message = "{validation.email.pattern}" )
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9\\s])\\S{9,31}$",
                message = "{validation.password.pattern}")
        String password
) {

}
