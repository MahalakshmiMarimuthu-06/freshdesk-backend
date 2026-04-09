package com.mahalakshmi.freshdesk.service;

import com.mahalakshmi.freshdesk.model.Ticket;
import com.mahalakshmi.freshdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repo;

    public TicketService(TicketRepository repo) {
        this.repo = repo;
    }

    public List<Ticket> getAllTickets() {
        return repo.findAll();
    }

    public Ticket getTicketById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public Ticket createTicket(Ticket ticket) {
        return repo.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        existing.setTitle(ticket.getTitle());
        existing.setDescription(ticket.getDescription());
        existing.setStatus(ticket.getStatus());
        existing.setPriority(ticket.getPriority());

        return repo.save(existing);
    }

    public void deleteTicket(Long id) {
        repo.deleteById(id);
    }

    public Ticket saveTicket(Ticket ticket) {
        return repo.save(ticket);
    }
}