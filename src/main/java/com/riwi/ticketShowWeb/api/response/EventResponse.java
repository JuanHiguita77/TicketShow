package com.riwi.ticketShowWeb.api.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    
    private Long id;
    private String title;
    private String city;
    private String category;
    private String description;
    private Date date;
    private String image_url;
    private double price;
    private int capacity;
    private List<SeatResponse> vacants;
}
