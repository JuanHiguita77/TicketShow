package com.riwi.ticketShowWeb.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.riwi.ticketShowWeb.domain.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> 
{
    Page<Event> findByCategoryContainingAndTitleContainingAndCityContaining(String category, String title, String city, Pageable pageable);
}