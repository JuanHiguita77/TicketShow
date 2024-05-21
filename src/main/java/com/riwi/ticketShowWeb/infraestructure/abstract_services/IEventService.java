package com.riwi.ticketShowWeb.infraestructure.abstract_services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.riwi.ticketShowWeb.api.dto.request.EventRequest;
import com.riwi.ticketShowWeb.api.dto.response.EventResponse;
import com.riwi.ticketShowWeb.infraestructure.services.CrudService;

public interface IEventService extends CrudService<EventRequest, EventResponse, Long>
{
    Page<EventResponse> searchEvent(String category, String title, String city, Pageable pageable);

    void sendEmail(Long idEvent, String email);

    EventResponse findById(Long id);
}
    

