package com.riwi.ticketShowWeb.api.response;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for event information")
public class EventResponse {
    
    @Schema(description =  "ID of the event")
    private Long id;

    @Schema(description = "Title of the event")
    private String title;

    @Schema(description = "City where the event takes place")
    private String city;

    @Schema(description = "Category of the event (e.g., theater, concerts)")
    private String category;

    @Schema(description = "Description of the event")
    private String description;

    @Schema(description = "Date on which the event will take place")
    private LocalDateTime date;

    @Schema(description = "URL of the event image")
    private String image_url;

    @Schema(description = "Price of the event")
    private double price;

    @Schema(description = "Event capacity")
    private int capacity;

    @Schema(description = "Event seating list")
    private List<SeatResponse> seats;
}
