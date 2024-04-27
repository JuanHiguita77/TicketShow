package com.riwi.ticketShowWeb.domain.entities;

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
public class Seat {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_event", referencedColumnName = "id")
    private Event event;
}
