package com.riwi.ticketShowWeb.api.response;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(description = "Response object for event information")
public class EventResponse {
    
    //@ApiModelProperty(notes = "ID of the event")
    private Long id;

    //@ApiModelProperty(notes = "Title of the event")
    private String title;

    //@ApiModelProperty(notes = "City where the event takes place")
    private String city;

    //@ApiModelProperty(notes = "Category of the event (e.g., theater, concerts)")
    private String category;

    //@ApiModelProperty(notes = "Description of the event")
    private String description;

    //@ApiModelProperty(notes = "Date on which the event will take place")
    private Date date;

    //@ApiModelProperty(notes = "URL of the event image")
    private String image_url;

    //@ApiModelProperty(notes = "Price of the event")
    private double price;

    //@ApiModelProperty(notes = "Event capacity")
    private int capacity;

    //@ApiModelProperty(notes = "Event seating list")
    private List<SeatResponse> seats;
}
