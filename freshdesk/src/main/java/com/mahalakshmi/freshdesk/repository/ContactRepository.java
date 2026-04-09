package com.mahalakshmi.freshdesk.repository;
import com.mahalakshmi.freshdesk.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}