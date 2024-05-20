package com.riwi.ticketShowWeb.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response object for Payload from Security Token")
public class PayloadResponse 
{
    @Schema(description =  "Sub name of the user token")
    private String email;

    @Schema(description =  "Role of user in token")
    private String role;

    @Schema(description =  "Time to token expire")
    private Long iat; 

    @Schema(description =  "Time to token expire")
    private Long exp; 
}
