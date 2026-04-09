package com.mahalakshmi.freshdesk.service;

import com.mahalakshmi.freshdesk.model.Contact;
import com.mahalakshmi.freshdesk.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repo;

    public ContactService(ContactRepository repo) {
        this.repo = repo;
    }

    public Contact saveContact(Contact contact) {
        return repo.save(contact);
    }

    public Contact updateContact(Long id, Contact contact) {
        Contact existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        existing.setName(contact.getName());
        existing.setEmail(contact.getEmail());
        existing.setPhone(contact.getPhone());

        return repo.save(existing);
    }

    public Contact getContactById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public void deleteContact(Long id) {
        repo.deleteById(id);
    }
    public List<Contact> getAllContacts() {
        return repo.findAll();
    }

}