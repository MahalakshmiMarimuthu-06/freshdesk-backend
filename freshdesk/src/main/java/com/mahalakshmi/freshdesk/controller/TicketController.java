package com.mahalakshmi.freshdesk.controller;

import com.mahalakshmi.freshdesk.dto.TicketDTO;
import com.mahalakshmi.freshdesk.model.Ticket;
import com.mahalakshmi.freshdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    // GET all
    @GetMapping
    public List<Ticket> getAll() {
        return service.getAllTickets();
    }

    // GET by ID
    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id) {
        return service.getTicketById(id);
    }

    // POST
    @PostMapping
    public Ticket create(@Valid @RequestBody TicketDTO dto) {
        Ticket ticket = new Ticket();

        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setStatus(dto.getStatus());
        ticket.setPriority(dto.getPriority());

        return service.createTicket(ticket);
    }

    // PUT
    @PutMapping("/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return service.updateTicket(id, ticket);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteTicket(id);
        return "Deleted successfully";
    }
}