package com.nhv.chatapp.entity;

import com.nhv.chatapp.entity.enums.MessageStatus;
import com.nhv.chatapp.entity.enums.MessageType;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "message")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "sentAt")
    private Instant sentAt;

    @Column(name = "editedAt")
    private Instant editedAt;

    @Column(name = "deletedAt")
    private Instant deletedAt;

    @Lob
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageType type = MessageType.TEXT;

    @Lob
    @Column(name = "messageStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus = MessageStatus.SENT;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chatRoomId", nullable = false)
    private Chatroom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyTo")
    private Message replyTo;

    @PrePersist
    public void prePersist() {
        this.sentAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.editedAt = Instant.now();
    }

    @PreDestroy
    public void preDestroy() {
        this.deletedAt = Instant.now();
    }

}