package com.mahalakshmi.freshdesk;

import com.mahalakshmi.freshdesk.model.Contact;
import com.mahalakshmi.freshdesk.repository.ContactRepository;
import com.mahalakshmi.freshdesk.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository repo;

    @InjectMocks
    private ContactService service;

    // 🟢 Test GET ALL
    @Test
    void testGetAllContacts() {
        Contact contact = new Contact();
        contact.setName("Maha");

        when(repo.findAll()).thenReturn(List.of(contact));

        List<Contact> result = service.getAllContacts();

        assertEquals(1, result.size());
        assertEquals("Maha", result.get(0).getName());
    }

    // 🟡 Test SAVE
    @Test
    void testSaveContact() {
        Contact contact = new Contact();
        contact.setName("Maha");

        when(repo.save(contact)).thenReturn(contact);

        Contact result = service.saveContact(contact);

        assertEquals("Maha", result.getName());
    }

    // 🔵 Test GET BY ID (SUCCESS)
    @Test
    void testGetContactById_Success() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Maha");

        when(repo.findById(1L)).thenReturn(Optional.of(contact));

        Contact result = service.getContactById(1L);

        assertEquals("Maha", result.getName());
    }

    // 🔴 Test GET BY ID (FAIL)
    @Test
    void testGetContactById_NotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.getContactById(1L));

        assertEquals("Contact not found", ex.getMessage());
    }

    // 🟠 Test UPDATE
    @Test
    void testUpdateContact() {
        Contact existing = new Contact();
        existing.setId(1L);
        existing.setName("Old");

        Contact updated = new Contact();
        updated.setName("New");

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);

        Contact result = service.updateContact(1L, updated);

        assertEquals("New", result.getName());
    }

    // ⚫ Test DELETE
    @Test
    void testDeleteContact() {
        doNothing().when(repo).deleteById(1L);

        service.deleteContact(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}