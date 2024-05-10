package com.riwi.ticketShowWeb.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.ticketShowWeb.domain.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> 
{
    
}
