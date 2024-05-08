package com.riwi.ticketShowWeb.api.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse 
{
    private String status;    
    private Integer code;   
}
