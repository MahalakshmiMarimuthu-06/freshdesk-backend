package com.mahalakshmi.freshdesk;

import com.mahalakshmi.freshdesk.model.Ticket;
import com.mahalakshmi.freshdesk.repository.TicketRepository;
import com.mahalakshmi.freshdesk.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository repo;

    @InjectMocks
    private TicketService service;

    // ✅ TEST SAVE
    @Test
    void testSaveTicket() {
        Ticket t = new Ticket();
        t.setTitle("Issue");

        when(repo.save(t)).thenReturn(t);

        Ticket result = service.saveTicket(t);

        assertEquals("Issue", result.getTitle());
    }

    // ✅ TEST GET BY ID SUCCESS
    @Test
    void testGetTicketById_Success() {
        Ticket t = new Ticket();
        t.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(t));

        Ticket result = service.getTicketById(1L);

        assertEquals(1L, result.getId());
    }

    // ❌ TEST GET BY ID NOT FOUND
    @Test
    void testGetTicketById_NotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.getTicketById(1L));

        assertEquals("Ticket not found", ex.getMessage());
    }

    // ✅ TEST DELETE
    @Test
    void testDeleteTicket() {
        doNothing().when(repo).deleteById(1L);

        service.deleteTicket(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}