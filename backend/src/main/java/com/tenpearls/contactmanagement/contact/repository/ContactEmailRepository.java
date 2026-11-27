package com.tenpearls.contactmanagement.contact.repository;

import com.tenpearls.contactmanagement.contact.entity.ContactEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactEmailRepository extends JpaRepository<ContactEmail, Long> {

    List<ContactEmail> findByContactId(Long contactId);

    Optional<ContactEmail> findByIdAndContactId(Long id, Long contactId);
}