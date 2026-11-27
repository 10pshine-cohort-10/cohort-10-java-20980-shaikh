package com.tenpearls.contactmanagement.contact.entity;

import com.tenpearls.contactmanagement.contact.entity.enums.EmailLabel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_emails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EmailLabel label;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
}