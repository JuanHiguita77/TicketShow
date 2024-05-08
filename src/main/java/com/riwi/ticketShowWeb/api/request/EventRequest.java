package com.riwi.ticketShowWeb.api.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Request object to update or create an event ")
public class EventRequest {

    @Size(
        min = 1,
        max = 45,
        message = "The title must have between 1 and 45 characters."
    )
    @NotBlank(message = "Event title required")
    @ApiModelProperty(notes = "Title to be carried by the event")
    private String title;

    @Size(
        min = 1,
        max = 45,
        message = "The city is required and must have between 1 and 45 characters."
    )
    @NotBlank(message = "The city is required")
    @ApiModelProperty(notes = "City where the event takes place")
    private String city;

    @Size(
        min = 1,
        max = 45,
        message = "The category is required and must have between 1 and 45 characters."
    )
    @NotBlank(message = "The city is required")
    @ApiModelProperty(notes = "Category of the event (e.g., theater, concerts)")
    private String category;

    @Size(
        min = 1,
        max = 45,
        message = "The description is required and must have between 1 and 45 characters."
    )
    @NotBlank(message = "The city is required")
    @ApiModelProperty(notes = "Description of the event")
    private String description;

    @NotNull(message = "The date is required")
    @Future(message = "The date must be in the future")
    @ApiModelProperty(notes = "Date on which the event will take place")
    private Date date;

    @Size(
        min = 1,
        max = 255,
        message = "The image is required"
    )
    @NotBlank(message = "The image is required")
    @ApiModelProperty(notes = "URL of the event image")
    private String image_url; 
    
    @DecimalMin(
    value = "0.01",
    message = "The service price value must be greater than 0"
    )
    @NotNull(message = "The price is required")
    @ApiModelProperty(notes = "Price of the event")
    private double price;

    @Max(value = 999999, message = "Capacity must be less than or equal to 999999")
    @ApiModelProperty(notes = "Event capacity")
    @NotNull(message = "The capacity is required")
    private int capacity;

}
