package com.nhv.chatapp.entity;

import com.nhv.chatapp.entity.enums.RoomRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "userchatroom")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userchatroom {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chatRoomId", nullable = false)
    private Chatroom chatRoom;

    @Lob
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomRole role;

    @Column(name = "joinedAt")
    private Instant joinedAt;

    @ColumnDefault("0")
    @Column(name = "isMuted")
    private Boolean isMuted = false;

    @ColumnDefault("0")
    @Column(name = "isPinned")
    private Boolean isPinned = false;

    @ColumnDefault("0")
    @Column(name = "canAddMembers")
    private Boolean canAddMembers = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastReadMessage")
    private Message lastReadMessage;

    @Column(name = "readAt")
    private Instant readAt;


    @PrePersist
    public void prePersist() {
        this.joinedAt = Instant.now();
        this.readAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.readAt = Instant.now();
    }
}