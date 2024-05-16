package com.riwi.ticketShowWeb.api.controllers;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.api.dto.request.EventRequest;
import com.riwi.ticketShowWeb.api.dto.response.EventResponse;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
@Tag(name = "Events")
public class EventController {

    @Autowired
    private final IEventService eventService;    
    @ApiResponse(
        responseCode = "400",
        description = "when send info is invalid",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @Operation(
        summary = "Create a new event",
        description = "send  information for create a new event"
    )
    @PostMapping(path = "/add")
    public ResponseEntity<EventResponse> save(@Validated @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.save(event));
    }
    
    @ApiResponse(
        responseCode = "400",
        description = "when id is invalid",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @Operation(
        summary = "Delete an event by id",
        description = "send the event id to delete it"
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Event is deleted succesfully");
        this.eventService.delete(id);
        return ResponseEntity.ok(response);
    }


    @ApiResponse(responseCode = "400", description = "when id is invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Update an event", description = "send information for update an event")
    @PutMapping(path = "/{id}")
    public ResponseEntity<EventResponse> update(@PathVariable Long id, @Validated @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.update(id, event));
    }


    @ApiResponse(responseCode = "400", description = "when category, city and title is invalid", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
})
    @Operation(summary = "search an event by category, title, city", description = "send the event title, category or city to search by this")
    @GetMapping(path = "/search")
    public Page<EventResponse> searchEvent(
            @RequestParam(value = "category", defaultValue = "", required = false) String category,
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "city", defaultValue = "", required = false) String city,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) 
    {
        Pageable pageable = PageRequest.of(page - 1, size);

        return this.eventService.searchEvent(category, title, city, pageable);
    }


}
