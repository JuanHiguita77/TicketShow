package com.riwi.ticketShowWeb.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Operation(
        summary = "List all events with pagination",
        description = "The page and page size must be sent to receive all events"
    )
    @GetMapping
    public ResponseEntity<Page<EventResponse>>listAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "2") int size) {
        return ResponseEntity.ok(this.eventService.list(page-1, size));
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
        summary = "bring an event by id",
        description = "you must send the id of the event to search"
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<EventResponse> searchById(@PathVariable Long id){
        return ResponseEntity.ok(this.eventService.findById(id));
    }

    @Operation(
        summary = "bring an event by title",
        description = "you must send the title of the event to search"
    )
    @GetMapping(path = "title/{title}")
    public ResponseEntity<EventResponse> searchByTtile(@PathVariable String title) {
        return ResponseEntity.ok(this.eventService.findByTitle(title));
    }

  @Operation(
        summary = "bring an event by city",
        description = "you must send the city of the event to search"
    )
    @GetMapping(path = "city/{city}")
    public ResponseEntity<EventResponse> searchByCity(@PathVariable String city) {
        return ResponseEntity.ok(this.eventService.findByCity(city));
    }
    
    @Operation(
        summary = "Create a new event",
        description = "send  information for create a new event"
    )
    @PostMapping()
    public ResponseEntity<EventResponse> save(@Validated @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.insert(event));
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
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Event is deleted succesfully");
        this.eventService.delete(id);
        return ResponseEntity.ok(response);
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
        summary = "Update an event",
        description = "send information for update an event"
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<EventResponse> update(@PathVariable Long id,@Validated @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.update(id, event));
    }
    
    
}
