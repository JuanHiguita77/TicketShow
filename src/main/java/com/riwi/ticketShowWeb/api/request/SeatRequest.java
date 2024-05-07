package com.riwi.ticketShowWeb.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {
    
    private Long id;
    private boolean available;
    private Long fk_id_event;
}
