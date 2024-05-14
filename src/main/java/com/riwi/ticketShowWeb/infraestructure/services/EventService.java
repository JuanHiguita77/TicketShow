package com.riwi.ticketShowWeb.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.api.dto.request.EventRequest;
import com.riwi.ticketShowWeb.api.dto.response.EventResponse;
import com.riwi.ticketShowWeb.api.dto.response.SeatResponse;
import com.riwi.ticketShowWeb.domain.entities.Event;
import com.riwi.ticketShowWeb.domain.entities.Seat;
import com.riwi.ticketShowWeb.domain.repositories.EventRepository;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IEventService;
import com.riwi.ticketShowWeb.utils.exceptions.BadRequestException;

import com.riwi.ticketShowWeb.domain.repositories.SeatRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EventService implements IEventService {
    
    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void delete(Long id) {
        Event event = this.find(id);
        this.eventRepository.delete(event);
    }
    
    @Override
    public EventResponse save(EventRequest request) {
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
    public Page<EventResponse> searchEvent(String category, String title, String city, Pageable pageable) {
        return eventRepository.findByCategoryContainingAndTitleContainingAndCityContaining(category, title, city, pageable)
        .map(this::entityToResponse);
    }


    @Override
    public EventResponse findById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    private Event find(Long id){
        return this.eventRepository.findById(id).orElseThrow(()-> new BadRequestException("Event"));
    }
    
    
    private EventResponse entityToResponse(Event entity){
        EventResponse response = new EventResponse();

        BeanUtils.copyProperties(entity, response);

        response.setSeats(entity.getSeats().stream().map(this::seatToResponse)
        .collect(Collectors.toList()));
        return response;
    }

    private SeatResponse seatToResponse(Seat entity){
        SeatResponse response = new SeatResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    
    private Event requestToEvent(EventRequest request, Event event) {
        BeanUtils.copyProperties(request, event);
    
        if (event.getId() == null) { // Verifica si el evento es nuevo
            if (event.getSeats() == null) {
                event.setSeats(new ArrayList<>());
            }
    
            // Crea los asientos solo si es un evento nuevo
            for (int i = 1; i <= event.getCapacity(); i++) 
            {
                Seat seat = new Seat();

                seat.setAvailable(true);
                seat.setEvent(event);
                
                event.getSeats().add(seat);
            }
        } 
        else 
        {
            // Si el evento ya existe, obtén los asientos existentes de la base de datos
            List<Seat> existingSeats = this.seatRepository.findByEventId(event.getId());
            
            // Si la nueva capacidad es menor que la capacidad actual, elimina los asientos adicionales
            if (event.getCapacity() < existingSeats.size()) {
                List<Seat> seatsToRemove = existingSeats.subList(event.getCapacity(), existingSeats.size());
                seatsToRemove.forEach(seat -> {
                    seatRepository.delete(seat);
                });
                existingSeats = existingSeats.subList(0, event.getCapacity());
            }
            
            // Si la nueva capacidad es mayor que la capacidad actual, agrega nuevos asientos
            if (event.getCapacity() > existingSeats.size()) {
                for (int i = existingSeats.size() + 1; i <= event.getCapacity(); i++) {
                    Seat seat = new Seat();
                    seat.setAvailable(true);
                    seat.setEvent(event);
                    existingSeats.add(seat);
                }
            }
            
            event.setSeats(existingSeats);
        }
    
        return event;
    }

}


