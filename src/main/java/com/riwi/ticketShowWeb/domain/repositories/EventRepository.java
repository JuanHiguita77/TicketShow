package com.riwi.ticketShowWeb.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.riwi.ticketShowWeb.domain.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    public Event findByTitle(String title);
    public Event findByCity(String city);
}
