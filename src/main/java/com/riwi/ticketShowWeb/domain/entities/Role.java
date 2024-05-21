package com.riwi.ticketShowWeb.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Entity(name = "role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description =  "Unique identifier for the role")
    private Long id;

    @Column(length = 45 ,nullable = false)
    @Schema(description =  "Name of the role")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(
        fetch = FetchType.EAGER,
        mappedBy = "role",
        orphanRemoval = false
    )
    @Schema(description =  "User associated with the role")
    private User user;
}
