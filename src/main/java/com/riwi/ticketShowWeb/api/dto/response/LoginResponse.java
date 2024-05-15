package com.riwi.ticketShowWeb.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Response object for login information")
public class LoginResponse {

    @Schema(description =  "ID of the user")
    private Long id;

    @Size(
        min = 1,
        max = 45,
        message = "The name must be between 1 and 45 characters"
    )
    @NotBlank(message = "The username  is required")
    @Schema(description =  "Name of the user")
    private String name;

    @Size(
        min = 8,
        max = 45,
        message = "The password must be between 8 and 45 characters"
    )
    @NotBlank(message = "The password is required")
    @Schema(description =  "Password of the user")
    private String password;
    
}
