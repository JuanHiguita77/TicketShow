package com.riwi.ticketShowWeb.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for seat information")
public class SeatResponse {
    
    @Schema(description = "ID of the seat")
    private Long id;

    @Schema(description = "Availability of seats")
    private boolean available;
}