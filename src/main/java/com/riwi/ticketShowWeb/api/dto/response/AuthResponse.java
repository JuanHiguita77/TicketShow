package com.riwi.ticketShowWeb.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for user information")
public class AuthResponse {
    
    @Schema(description = "A message related to the response")
    private String message;

    @Schema(description = "A token associated with the user")
    private String token;
}
