package com.riwi.ticketShowWeb.domain.entities;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "rol")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(description = "Entity representing a role")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @ApiModelProperty(notes = "Unique identifier for the role")
    private Long id;

    @Column(length = 45 ,nullable = false)
   // @ApiModelProperty(notes = "Name of the role")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(
        fetch = FetchType.EAGER,
        mappedBy = "rol",
        orphanRemoval = false
    )
   // @ApiModelProperty(notes = "User associated with the role")
    private User user;
}
