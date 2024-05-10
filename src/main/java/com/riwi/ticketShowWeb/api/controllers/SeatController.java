package com.riwi.ticketShowWeb.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.api.dto.errors.ErrorsResp;
import com.riwi.ticketShowWeb.api.request.SeatRequest;
import com.riwi.ticketShowWeb.api.response.SeatResponse;
import com.riwi.ticketShowWeb.domain.entities.Seat;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.ISeatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/seats")
@AllArgsConstructor
@Tag(name = "Seats", description = "Seats Operations")
public class SeatController 
{
    @Autowired
    private final ISeatService iseatService;

    //Swagger error documentation
    @ApiResponse(
        responseCode = "400",
        description = "When the id is not valid",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorsResp.class)
            )
        }
    )

    @Operation(
        summary = "Endpoint to list all Seats",
        description = "To use you need send a number page and size to get all vacants paginate"
    )
    @GetMapping
    public ResponseEntity<Page<SeatResponse>> listAll(@RequestParam(defaultValue = "1") int page, @RequestParam int size) 
    {
        return ResponseEntity.ok(this.iseatService.listAll(page -1, size));
    }
    
    @Operation(
        summary = "Endpoint to list all Paginate vacants",
        description = "To use you need send a number page and size to get all vacants paginate"
    )
    @PostMapping("/add")
    public ResponseEntity<SeatResponse> save(@Validated @RequestBody SeatRequest request)
    {
        return ResponseEntity.ok(this.iseatService.save(request));
    }

    @Operation(
        summary = "Endpoint to list all Paginate vacants",
        description = "To use you need send a number page and size to get all vacants paginate"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id)
    {
        this.iseatService.delete(id);

        Map<String, String> response = new HashMap<>();

        response.put("message", "Deleted Sucessfully");

        return ResponseEntity.ok(response);
    }

}
