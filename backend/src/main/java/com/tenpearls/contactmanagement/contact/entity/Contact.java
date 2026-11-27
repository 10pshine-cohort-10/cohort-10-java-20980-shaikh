package com.tenpearls.contactmanagement.contact.entity;

import com.tenpearls.contactmanagement.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ContactEmail> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ContactPhone> phones = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public void addEmail(ContactEmail email) {
        if (email.getContact() == this) {
            return;
        }
        if (email.getContact() != null) {
            email.getContact().emails.remove(email); // detach from old parent
        }
        emails.add(email);
        email.setContact(this);
    }

    public void removeEmail(ContactEmail email) {
        if (emails.remove(email)) {
            email.setContact(null);
        }
    }

    public void addPhone(ContactPhone phone) {
        if (phone.getContact() == this) {
            return;
        }
        if (phone.getContact() != null) {
            phone.getContact().phones.remove(phone);
        }
        phones.add(phone);
        phone.setContact(this);
    }

    public void removePhone(ContactPhone phone) {
        if (phones.remove(phone)) {
            phone.setContact(null);
        }
    }

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}