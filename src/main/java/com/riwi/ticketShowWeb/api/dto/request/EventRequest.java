package com.riwi.ticketShowWeb.api.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for event information")
public class EventRequest {

    @Size(
        min = 1,
        max = 45,
        message = "The title must have between 1 and 45 characters."
    )
    @NotBlank(message = "Event title required")
    @Schema(description = "Title to be carried by the event")
    private String title;

    @Size(
        min = 1,
        max = 45,
        message = "The city is required and must have between 1 and 45 characters."
    )
    @NotBlank(message = "The city is required")
    @Schema(description = "City where the event takes place")
    private String city;

    @Size(
        min = 1,
        max = 45,
        message = "The category is required and must have between 1 and 45 characters."
    )
    @NotBlank(message = "The city is required")
    @Schema(description =  "Category of the event (e.g., theater, concerts)")
    private String category;

    @Size(
        min = 1,
        max = 45,
        message = "The description is required and must have between 1 and 45 characters."
    )
    @NotBlank(message = "The city is required")
    @Schema(description = "Description of the event")
    private String description;

    @NotNull(message = "The date is required")
    @Future(message = "The date must be in the future")
    @Schema(description =  "Date on which the event will take place")
    private LocalDateTime date;

    @Size(
        min = 1,
        max = 800,
        message = "The image is required"
    )
    @NotBlank(message = "The image is required")
    @Schema(description = "URL of the event image")
    private String image_url; 
    
    @DecimalMin(
    value = "0.01",
    message = "The service price value must be greater than 0"
    )
    @NotNull(message = "The price is required")
    @Schema(description =  "Price of the event")
    private double price;

    @Max(value = 80, message = "Capacity must be less than or equal to 80")
    @Min(value = 5, message = "Capacity must be highter than or equal to 5")
    @Schema(description = "Event capacity")
    @NotNull(message = "The capacity is required")
    private int capacity;

}
