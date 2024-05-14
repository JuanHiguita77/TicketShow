package com.riwi.ticketShowWeb.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity that represents a seat")
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the seat")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Availability status of the seat")
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(description = "Seat availability status")
    @JoinColumn(name = "fk_id_event", referencedColumnName = "id")
    private Event event;
}
