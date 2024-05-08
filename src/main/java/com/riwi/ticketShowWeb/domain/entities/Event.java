package com.riwi.ticketShowWeb.domain.entities;

import java.sql.Date;
import java.util.List;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
//@ApiModel(description = "Entity representing an event")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ApiModelProperty(notes = "Unique identifier for the event")
    private Long id;

    @Column(length = 45, nullable = false)
    //@ApiModelProperty(notes = "Title of the event")
    private String title;

    @Column(length = 45, nullable = false)
    //@ApiModelProperty(notes = "City where the event takes place")
    private String city;

    @Column(length = 45, nullable = false)
    //@ApiModelProperty(notes = "Category of the event")
    private String category;

    @Column(length = 45, nullable = false)
    //@ApiModelProperty(notes = "Description of the event")
    private String description;

    @Column(nullable = false)
    //@ApiModelProperty(notes = "Date of the event")
    private Date date;

    @Column(length = 255, nullable = false)
   // @ApiModelProperty(notes = "URL of the event image")
    private String image_url;

    @Column(nullable = false)
    //@ApiModelProperty(notes = "Price of the event")
    private double price;

    @Column(nullable = false)
    //@ApiModelProperty(notes = "Capacity of the event")
    private int capacity;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "event",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    //@ApiModelProperty(notes = "List of seats associated with the event")
    private List<Seat> seat;

}
