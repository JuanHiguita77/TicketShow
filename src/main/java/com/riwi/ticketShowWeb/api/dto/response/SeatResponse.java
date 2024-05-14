package com.riwi.ticketShowWeb.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for seat information")
public class SeatResponse {
    
    @Schema(description = "ID of the seat")
    private Long id;

    @Schema(description = "Availability of seats")
    private boolean available;
}
