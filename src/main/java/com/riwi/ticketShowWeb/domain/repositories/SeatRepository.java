package com.riwi.ticketShowWeb.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.riwi.ticketShowWeb.domain.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> 
{
    @Query("UPDATE Seat s SET s.available = false WHERE s.event.id = :idEvent AND s.id IN :selectedSeats")
    void choosedSeats(Long idEvent, List<Long> selectedSeats);
}
