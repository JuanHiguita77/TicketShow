package com.riwi.ticketShowWeb.infraestructure.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.domain.entities.Event;
import com.riwi.ticketShowWeb.domain.entities.Seat;
import com.riwi.ticketShowWeb.infraestructure.services.interfaces.EventRequest;
import com.riwi.ticketShowWeb.infraestructure.services.interfaces.EventResponse;
import com.riwi.ticketShowWeb.infraestructure.services.interfaces.IEventService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EventService implements IEventService {

    @Autowired
    private final EventRespository eventRespository;

    @Override
    public void delete(Long id) {
        Event event = this.find(id);
        this.eventRespository.delete(event);
    }

    @Override
    public EventResponse save(EventRequest request) {
        Event event = this.requestToEvent(request, new Event());
        return  this.entityToResponse(this.eventRespository.save(event));
    }

    @Override
    public EventResponse update(EventRequest request, Long id) {
        Event event = this.find(id);
        Event eventUpdate = this.requestToEvent(request, event);
        return this.entityToResponse(this.eventRespository.save(event));
    }

    @Override
    public Page<EventResponse> listAll(int page, int size) {
        if (page<0) {
            page = 0;
        }

       PageRequest pagination =  PageRequest.of(page, size);
       return this.eventRespository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public EventResponse searchByTitle(String title) {
        return this.eventRespository.findByTitle(title).orElseThrow();
    }

    private EventResponse entityToResponse(Event entity){
        EventResponse response = new EventResponse();
        BeanUtils.copyProperties(entity, response);
        response.setSeats(entity.getSeats().stream().map(seat->this.seatToResponse(seat))
        .collect(Collectors.toList()));
        return response;
    }
    

    private SeatToEventResponse seatToResponse(Seat entity){
        SeatToEventResponse response = new SeatToEventResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    
    private Event requestToEvent(EventRequest request, Event event){
        BeanUtils.copyProperties(request, event);
        event.setSeats(new ArrayList<>());
        return event;
    }

    private Event find(Long id){
        return this.eventRespository.findById(id).orElseThrow();
    }
}
