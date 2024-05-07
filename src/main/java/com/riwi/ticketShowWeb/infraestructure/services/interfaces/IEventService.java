package com.riwi.ticketShowWeb.infraestructure.services.interfaces;

public interface IEventService extends CrudEvents<EventRequest, EventResponse, Long>{
    public EventResponse findByTitle(String title);
}
