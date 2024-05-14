package com.riwi.ticketShowWeb.infraestructure.abstract_services;

import com.riwi.ticketShowWeb.api.dto.request.EventRequest;
import com.riwi.ticketShowWeb.api.dto.response.EventResponse;
import com.riwi.ticketShowWeb.infraestructure.services.CrudService;

public interface IEventService extends CrudService<EventRequest, EventResponse, Long>{
    public EventResponse findByTitle(String title);
    public EventResponse findById(Long id);
    public EventResponse findByCity(String city);
}
