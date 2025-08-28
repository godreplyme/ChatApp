package com.nhv.chatapp.entity;

import com.nhv.chatapp.entity.enums.ContactStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contactUser", nullable = false)
    private User contactUser;

    @Column(name = "alias", length = 100)
    private String alias;

    @Lob
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactStatus status = ContactStatus.ACTIVE;

    @Column(name = "createAt", nullable = false)
    private Instant createAt;

    @Column(name = "updateAt")
    private Instant updateAt;

    @PrePersist
    public void prePersist() {
        if (this.createAt == null) {
            this.createAt = Instant.now();
        }
        this.updateAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updateAt = Instant.now();
    }

}