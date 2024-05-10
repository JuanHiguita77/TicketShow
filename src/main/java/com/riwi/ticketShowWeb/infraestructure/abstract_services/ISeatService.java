package com.riwi.ticketShowWeb.infraestructure.abstract_services;

import com.riwi.ticketShowWeb.api.request.SeatRequest;
import com.riwi.ticketShowWeb.api.response.SeatResponse;
import com.riwi.ticketShowWeb.infraestructure.services.CrudService;


public interface ISeatService extends CrudService<SeatRequest, SeatResponse, Long> 
{
    public SeatResponse findById(Long id);
}