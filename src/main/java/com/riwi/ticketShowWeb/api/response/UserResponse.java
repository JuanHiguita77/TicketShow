package com.riwi.ticketShowWeb.api.response;

import java.util.Date;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(description = "Response object for user information")
public class UserResponse {
    
   // @ApiModelProperty(notes = "ID of the user")
    private Long id;

   // @ApiModelProperty(notes = "Name of the user")
    private String name;

   // @ApiModelProperty(notes = "Password of the user")
    private String password;

   // @ApiModelProperty(notes = "Email address of the user")
    private String email;

   // @ApiModelProperty(notes = "ID of the role associated with the user")
    private Long rol_id;

   // @ApiModelProperty(notes = "Date when the user was deleted")
    private Date deleteAt;
}
