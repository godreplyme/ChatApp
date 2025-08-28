package com.nhv.chatapp.entity;

import com.nhv.chatapp.entity.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "chatroom")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chatroom {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Lob
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType type = RoomType.PRIVATE;

    @Column(name = "groupName", length = 100)
    private String groupName;

    @Column(name = "groupAvatar")
    private String groupAvatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastMessage")
    private Message lastMessage;

    @Column(name = "createAt", nullable = false)
    private Instant createAt;

    @Column(name = "updateAt")
    private Instant updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private User createdBy;

    //chua them pinned, muted

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