package com.riwi.ticketShowWeb.api.dto.request;

import com.riwi.ticketShowWeb.domain.entities.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Size(
        min = 1,
        max = 45,
        message = "The name must be between 1 and 45 characters"
    )
    @NotBlank(message = "The name is required")
    @Schema(description =  "Name of the user")
    private String userName;

    @Size(
        min = 8,
        max = 45,
        message = "The password must be between 8 and 45 characters"
    )
    @NotBlank(message = "The password is required")
    @Schema(description =  "Password of the user")
    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "The email is required")
    @Schema(description =  "Email address of the user")
    private String email;

    @NotNull(message = "The Role id is required")
    @Schema(description =  "Role of the new user")
    private Long role_id;
    
}
