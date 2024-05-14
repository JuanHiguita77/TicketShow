package com.riwi.ticketShowWeb.infraestructure.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.api.dto.request.EventRequest;
import com.riwi.ticketShowWeb.api.dto.response.EventResponse;
import com.riwi.ticketShowWeb.api.dto.response.SeatResponse;
import com.riwi.ticketShowWeb.domain.entities.Event;
import com.riwi.ticketShowWeb.domain.entities.Seat;
import com.riwi.ticketShowWeb.domain.repositories.EventRepository;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IEventService;
import com.riwi.ticketShowWeb.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EventService implements IEventService {
    
    @Autowired
    private final EventRepository eventRepository;
    
    @Override
    public void delete(Long id) {
        Event event = this.find(id);
        this.eventRepository.delete(event);
    }
    
    @Override
    public EventResponse insert(EventRequest request) {
        Event event = this.requestToEvent(request, new Event());
        return  this.entityToResponse(this.eventRepository.save(event));
    }
    
    @Override
    public EventResponse update(Long id, EventRequest request) {
        Event event = this.find(id);
        Event eventUpdate = this.requestToEvent(request, event);
        return this.entityToResponse(this.eventRepository.save(eventUpdate));
    }
    @Override
    public Page<EventResponse> list(int page, int size) {
        if (page<0) {
            page = 0;
        }
       PageRequest pagination =  PageRequest.of(page, size);
       return this.eventRepository.findAll(pagination).map(this::entityToResponse);
    }
    
    @Override
    public EventResponse findById(Long id) {
        return this.entityToResponse(this.find(id));
    }
   /* 
    @Override
    public EventResponse findByTitle(String title) {
        return this.entityToResponse(this.eventRepository.findByTitle(title));
    }
    
    @Override
    public EventResponse findByCity(String city) {
        return this.entityToResponse(this.eventRepository.findByCity(city));
    }
    
    */
    private Event find(Long id){
        return this.eventRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Event"));
    }

    
    private EventResponse entityToResponse(Event entity){
        EventResponse response = new EventResponse();
        BeanUtils.copyProperties(entity, response);
        response.setSeats(entity.getSeats().stream().map(seat->this.seatToResponse(seat))
        .collect(Collectors.toList()));
        return response;
    }
    

    private SeatResponse seatToResponse(Seat entity){
        SeatResponse response = new SeatResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    
    private Event requestToEvent(EventRequest request, Event event){
        BeanUtils.copyProperties(request, event);
        event.setSeats(new ArrayList<>());
        return event;
    }

    @Override
    public Page<EventResponse> searchEvent(String category, String title, String city, Pageable pageable) {
        return eventRepository.findByCategoryContainingAndTitleContainingAndCityContaining(category, title, city, pageable)
        .map(this::entityToResponse);
    }

}


