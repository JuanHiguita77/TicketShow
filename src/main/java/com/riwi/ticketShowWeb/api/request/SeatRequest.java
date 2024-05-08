package com.riwi.ticketShowWeb.api.request;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(description = "Request object for seat information")
public class SeatRequest {
    
    //@ApiModelProperty(notes = "ID of the seat")
    @NotBlank(message = "The seat ID is mandatory")
    @Min(value = 1, message = "The ID should be greater than zero")
    private Long id;

    //@ApiModelProperty(notes = "Seat availability status")
    @NotNull(message = "The availability status of the seat is required")
    private boolean available;

    @NotNull(message = "The foreign key ID of the event is required")
    @Min(value = 1, message = "The foreign key ID must be greater than zero")
    //@ApiModelProperty(notes = "Foreign key ID of the event associated with the seat")
    private Long fk_id_event;
}
