package com.riwi.ticketShowWeb.api.dto.response;

import java.sql.Date;

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
public class UserResponse {
    
    @Schema(description =  "ID of the user")
    private Long id;

    @Schema(description =  "Name of the user")
    private String name;

    @Schema(description =  "Password of the user")
    private String password;

    @Schema(description =  "Email address of the user")
    private String email;

    @Schema(description =  "ID of the role associated with the user")
    private Long rol_id;

    @Schema(description =  "Date when the user was deleted")
    private Date deleteAt;
}
