package dev.mikel_villota.vlrhub_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "direct_message")
@Data
public class DirectMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dm_id")
    private Long dmId;

    @ManyToOne
    @JoinColumn(name = "sender_user", nullable = false)
    private UserEntity senderUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user", nullable = false)
    private UserEntity receiverUser;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_story", columnDefinition = "boolean default false")
    private boolean isStory;
}