package com.mahalakshmi.freshdesk.controller;

import com.mahalakshmi.freshdesk.dto.ContactDTO;
import com.mahalakshmi.freshdesk.model.Contact;
import com.mahalakshmi.freshdesk.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    // GET
    @GetMapping
    public List<Contact> getAll() {
        return service.getAllContacts();
    }

    // POST
    @PostMapping
    public Contact create(@Valid @RequestBody ContactDTO dto) {
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());

        return service.saveContact(contact);
    }

    // PUT
    @PutMapping("/{id}")
    public Contact update(@PathVariable Long id, @RequestBody Contact contact) {
        return service.updateContact(id, contact);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteContact(id);
        return "Deleted successfully";
    }
    @GetMapping("/{id}")
    public Contact getById(@PathVariable Long id) {
        return service.getContactById(id);
    }
}