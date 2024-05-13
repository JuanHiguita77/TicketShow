package com.riwi.ticketShowWeb.infraestructure.abstract_services;

import java.util.List;

public interface ISeatService
{
   public void choosedSeats(Long idEvent, List<Long> selectedSeats);    
}
