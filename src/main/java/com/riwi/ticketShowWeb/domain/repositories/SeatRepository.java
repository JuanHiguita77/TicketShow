package com.riwi.ticketShowWeb.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riwi.ticketShowWeb.domain.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> 
{
    //Modificar asientos a false cuando se seleccionan
    @Modifying
    @Query(value = "update seat s SET s.available = false WHERE s.event.id = :idEvent AND s.id IN (:selectedSeats)")
    int selectedSeats(@Param("idEvent") Long idEvent, @Param("selectedSeats") List<Long> selectedSeats);

    //Traer los asientos de cada evento
    List<Seat> findByEventId(Long eventId);

}
