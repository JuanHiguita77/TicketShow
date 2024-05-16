package com.riwi.ticketShowWeb.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for register information")
public class RegisterResponse {

    @Schema(description =  "Registered user id")
    private Long id;

    @Size(
        min = 1,
        max = 45,
        message = "The name must be between 1 and 45 characters"
    )
    @NotBlank(message = "The name is required")
    @Schema(description =  "Name of registered user")
    private String name;

    @Size(
        min = 8,
        max = 45,
        message = "The password must be between 8 and 45 characters"
    )
    @NotBlank(message = "The password is required")
    @Schema(description =  "Registered user password")
    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "The email is required")
    @Schema(description =  "E-mail address of the registered user")
    private String email;
    
}
