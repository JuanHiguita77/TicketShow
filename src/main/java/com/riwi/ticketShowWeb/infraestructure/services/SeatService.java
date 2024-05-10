package com.riwi.ticketShowWeb.infraestructure.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.domain.repositories.SeatRepository;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.ISeatService;
import com.riwi.ticketShowWeb.api.request.SeatRequest;
import com.riwi.ticketShowWeb.api.response.SeatResponse;
import com.riwi.ticketShowWeb.domain.entities.Event;
import com.riwi.ticketShowWeb.domain.entities.Seat;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeatService implements ISeatService
{
    @Autowired
    private final SeatRepository seatRepository;

    @Autowired
    private final EventRepository eventRepository;

    @Override
    public Page<SeatResponse> listAll(int page, int size) 
    {
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.seatRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public SeatResponse save(SeatRequest request)
    {
        Seat seat = this.requestToSeat(request, new Seat());

        return this.entityToResponse(this.seatRepository.save(seat));
    }

    @Override
    public void delete(Long id)
    {
        Seat seat = this.find(id);

        this.seatRepository.delete(seat);
    }



    private SeatResponse entityToResponse(Seat seat)
    {
        SeatResponse response = new SeatResponse();
 
        BeanUtils.copyProperties(seat, response);
        
        return response;
    }

    private Seat requestToSeat(com.riwi.ticketShowWeb.api.request.SeatRequest request, Seat seat)
    {
        BeanUtils.copyProperties(request, seat);

        return seat;
    }

    private Seat find(Long id)
    {
        return this.seatRepository.findById(id).orElseThrow(() -> new BadRequestException("Seat not found"));
    }





    //Dont use
    @Override
    public SeatResponse update(Long id,SeatRequest request) 
    {
        return null;
    }
}
