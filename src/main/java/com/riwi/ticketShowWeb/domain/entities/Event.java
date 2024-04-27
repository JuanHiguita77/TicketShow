package com.riwi.ticketShowWeb.domain.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String title;

    @Column(length = 45, nullable = false)
    private String city;

    @Column(length = 45, nullable = false)
    private String category;

    @Column(length = 45, nullable = false)
    private String description;

    @Column(nullable = false)
    private Date date;

    @Column(length = 255, nullable = false)
    private String image_url;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Seat> seats;
}
