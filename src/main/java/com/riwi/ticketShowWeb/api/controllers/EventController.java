package com.riwi.ticketShowWeb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.riwi.ticketShowWeb.infraestructure.services.interfaces.IEventService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {
    
    @Autowired
    private final IEventService eventService;
    @GetMapping
    public ResponseEntity<Page<EventResponse>>listAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "2") int size) {
        return ResponseEntity.ok(this.eventService.listAll(page-1, size));
    }

    @GetMapping(path = "/{title}")
    public ResponseEntity<EventResponse> searchByTtile(@PathVariable String title) {
        return ResponseEntity.ok(this.eventService.findByTitle(title));
    }
    
    @PostMapping()
    public ResponseEntity<EventResponse> save(@RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.save(event));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EventResponse> update(@PathVariable Long id, @RequestBody EventRequest event) {
        return ResponseEntity.ok(this.eventService.update(id, event));
    }
    
    
}
