package com.mahalakshmi.freshdesk.repository;

import com.mahalakshmi.freshdesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}