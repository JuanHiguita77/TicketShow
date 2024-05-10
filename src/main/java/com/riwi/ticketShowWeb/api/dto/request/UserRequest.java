package com.riwi.ticketShowWeb.api.dto.request;

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
@Schema(description = "Request object for user information")
public class UserRequest {

    @Size(
        min = 1,
        max = 45,
        message = "The name must be between 1 and 100 characters"
    )
    @NotBlank(message = "The name is required")
    @Schema(description =  "Name of the user")
    private String name;

    @Size(
        min = 8,
        max = 255,
        message = "The password must be between 8 and 255 characters"
    )
    @NotBlank(message = "The password is required")
    @Schema(description =  "Password of the user")
    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "The email is required")
    @Schema(description =  "Email address of the user")
    private String email;
}
