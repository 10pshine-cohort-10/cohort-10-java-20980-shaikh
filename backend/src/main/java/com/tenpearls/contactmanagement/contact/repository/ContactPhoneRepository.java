package com.tenpearls.contactmanagement.contact.repository;

import com.tenpearls.contactmanagement.contact.entity.ContactPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactPhoneRepository extends JpaRepository<ContactPhone, Long> {

    List<ContactPhone> findByContactId(Long contactId);

    Optional<ContactPhone> findByIdAndContactId(Long id, Long contactId);
}