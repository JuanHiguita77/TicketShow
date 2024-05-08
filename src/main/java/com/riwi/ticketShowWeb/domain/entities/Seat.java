package com.riwi.ticketShowWeb.domain.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "seat")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Entity that represents a seat")
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique identifier for the seat")
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Availability status of the seat")
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @ApiModelProperty(notes = "Seat availability status")
    @JoinColumn(name = "fk_id_event", referencedColumnName = "id")
    private Event event;
}
