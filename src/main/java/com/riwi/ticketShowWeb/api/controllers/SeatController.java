package com.riwi.ticketShowWeb.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.infraestructure.abstract_services.ISeatService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@RequestMapping("/seat")
@Tag(name = "Seats")
public class SeatController
{
    @Autowired
    private final ISeatService iSeatService;
    
    @ApiResponse(
        responseCode = "400",
        description = "when id event is invalid",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @Operation(
        summary = "Select a Seat",
        description = "Sent the event id and a list type number with seats id, just when the user add the event at shopping cart"
    )
    @PatchMapping("/selectSeat/{idEvent}")
    public void selectSeat(@PathVariable Long idEvent, @RequestBody List<Long> selectedSeats) 
    {
        this.iSeatService.choosedSeats(idEvent, selectedSeats);
    }
}
