package com.riwi.ticketShowWeb.infraestructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.domain.repositories.SeatRepository;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.ISeatService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeatService implements ISeatService
{
    @Autowired
    private final SeatRepository seatRepository;

    //Marcar asientos disponible
    @Override
    @Transactional
    public void choosedSeats(Long idEvent, List<Long> selectedSeats) 
    {
        this.seatRepository.selectedSeats(idEvent, selectedSeats);
    }
}
