package com.riwi.ticketShowWeb.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for seat information")
public class SeatRequest {
    
    @Schema(description = "ID of the seat")
    @NotBlank(message = "The seat ID is mandatory")
    @Min(value = 1, message = "The ID should be greater than zero")
    private Long id;

    @Schema(description =  "Seat availability status")
    @NotNull(message = "The availability status of the seat is required")
    private boolean available;

    @NotNull(message = "The foreign key ID of the event is required")
    @Min(value = 1, message = "The foreign key ID must be greater than zero")
    @Schema(description =  "Foreign key ID of the event associated with the seat")
    private Long fk_id_event;
}
