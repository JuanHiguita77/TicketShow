package com.riwi.ticketShowWeb.api.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @NotBlank("message = "El titulo es requerido")
    @Size(
        min = 1,
        max = 100,
        message = "EL titulo debe tener entre 0 y 100 caracteres"
    )
    private String title;

    @NotBlank("message = "La ciudad es requerido")
    @Size(
        min = 1,
        max = 100,
        message = "La ciudad debe tener entre 0 y 100 caracteres"
    )
    private String city;

    @NotBlank("message = "La categoria es requerida")
    @Size(
        min = 1,
        max = 100,
        message = "La categoria debe tener entre 0 y 100 caracteres"
    )
    private String category;

    @NotBlank("message = "La descripcion es requerida")
    @Size(
        min = 1,
        max = 100,
        message = "La descripcion debe tener entre 0 y 100 caracteres"
    )
    private String description;

    @Future
    @NotBlank("La fecha debe de ser requerida")
    private Date date;

    @NotBlank(message = "La imagen es requerido")
    @Size(
        min = 1,
        max = 100,
        message = "EL nombre debe de tener entre 1 y 100 caracteres"
    )
    private String image_url;

    @NotBlank(message = "El precio es requerido")
    @DecimalMin(
        value = "0,01",
        message = "El valor del servicio debe ser mayor a 0"
    )    
    private double price;

    @Max(value = 999999)
    @NotNull(message = "La capacidad es obigatoria")
    private int capacity;

}
