package dev.mikel_v.vhub_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Direct Message Entity.
 *
 * This class represents a direct message entity in the application.
 * It is mapped to the "direct_messages" table in the database.
 *
 * Note: This class is annotated with @Entity to indicate that it is a persistent entity.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Entity
@Table(name = "direct_messages")
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