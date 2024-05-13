package com.riwi.ticketShowWeb.domain.entities;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "event")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing an event")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the event")
    private Long id;

    @Column(length = 45, nullable = false)
    @Schema(description = "Title of the event")
    private String title;

    @Column(length = 45, nullable = false)
    @Schema(description = "City where the event takes place")
    private String city;

    @Column(length = 45, nullable = false)
    @Schema(description = "Category of the event")
    private String category;

    @Column(length = 45, nullable = false)
    @Schema(description = "Description of the event")
    private String description;

    @Column(nullable = false)
    @Schema(description = "Date of the event")
    private LocalDate date;

    @Column(length = 255, nullable = false)
    @Schema(description = "URL of the event image")
    private String image_url;

    @Column(nullable = false)
    @Schema(description = "Price of the event")
    private double price;

    @Column(nullable = false)
    @Min(value = 5, message = "Capacity must be at least 5")
    @Max(value = 80, message = "Capacity cannot exceed 80")
    @Schema(description = "Capacity of the event")
    private int capacity;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "event",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    @Schema(description =  "List of seats associated with the event")
    private List<Seat> seats;
    
}
