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

    @Schema(description =  "User login ID")
    private Long id;

    @Size(
        min = 1,
        max = 45,
        message = "The name must be between 1 and 45 characters"
    )
    @NotBlank(message = "The username  is required")
    @Schema(description =  "User login name")
    private String name;

    @Size(
        min = 8,
        max = 45,
        message = "The password must be between 8 and 45 characters"
    )
    @NotBlank(message = "The password is required")
    @Schema(description =  "User login password")
    private String password;
    
}
