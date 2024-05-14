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

    @ApiResponse(
        responseCode = "400",
        description = "when page or size are not int numbers",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @Operation(
        summary = "List all events with pagination",
        description = "The page and page size must be sent to receive all events"
    )
    @GetMapping
    public ResponseEntity<Page<EventResponse>>listAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "4") int size) {
        return ResponseEntity.ok(this.eventService.listAll(page - 1, size));
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

    @ApiResponse(
        responseCode = "400",
        description = "when send title is invalid",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @Operation(
        summary = "bring an event by title",
        description = "you must send the title of the event to search"
    )
    @GetMapping(path = "/title/{title}")
    public ResponseEntity<EventResponse> searchByTitle(@PathVariable String title) {
        return ResponseEntity.ok(this.eventService.findByTitle(title));
    }
    
    @ApiResponse(
        responseCode = "400",
        description = "when send city is invalid",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @Operation(
        summary = "bring an event by city",
        description = "you must send the city of the event to search"
    )
    @GetMapping(path = "/city/{city}")
    public ResponseEntity<EventResponse> searchByCity(@PathVariable String city) {
        return ResponseEntity.ok(this.eventService.findByCity(city));
    }
    
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

    @ApiResponse(
        responseCode = "400",
        description = "when id or send event info are invalid",
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
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<EventResponse> update(@PathVariable Long id, @Validated @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.update(id, event));
    }
}
