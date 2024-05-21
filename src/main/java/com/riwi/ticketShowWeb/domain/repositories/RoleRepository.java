package com.riwi.ticketShowWeb.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.ticketShowWeb.domain.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{}
