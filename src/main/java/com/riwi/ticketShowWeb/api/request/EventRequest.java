package com.riwi.ticketShowWeb.api.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    
    private String title;
    private String city;
    private String category;
    private String description;
    private Date date;
    private String image_url;
    private double price;
    private int capacity;

}
