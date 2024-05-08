package com.riwi.ticketShowWeb.api.response;

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
//@ApiModel(description = "Response object for seat information")
public class SeatResponse {
    
    //@ApiModelProperty(notes = "ID of the seat")
    private Long id;

    //@ApiModelProperty(notes = "Availability of seats")
    private boolean available;
}
