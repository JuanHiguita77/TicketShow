package com.riwi.ticketShowWeb.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.riwi.ticketShowWeb.api.dto.request.EventRequest;
import com.riwi.ticketShowWeb.api.dto.response.EventResponse;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private final IEventService eventService;
    @GetMapping
    public ResponseEntity<Page<EventResponse>>listAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "2") int size) {
        return ResponseEntity.ok(this.eventService.list(page-1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EventResponse> searchById(@PathVariable Long id){
        return ResponseEntity.ok(this.eventService.findById(id));
    }

    @GetMapping(path = "title/{title}")
    public ResponseEntity<EventResponse> searchByTtile(@PathVariable String title) {
        return ResponseEntity.ok(this.eventService.findByTitle(title));
    }

    @GetMapping(path = "city/{city}")
    public ResponseEntity<EventResponse> searchByCity(@PathVariable String city) {
        return ResponseEntity.ok(this.eventService.findByCity(city));
    }
    
    
    @PostMapping()
    public ResponseEntity<EventResponse> save(@Validated @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.insert(event));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Event is deleted succesfully");
        this.eventService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EventResponse> update(@PathVariable Long id,@Validated @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.update(id, event));
    }
    
    
}
