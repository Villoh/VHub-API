package dev.mikel_v.vhub_api.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Story Entity.
 *
 * This class represents a story entity in the application.
 * It is mapped to the "stories" table in the database.
 *
 * Note: This class is annotated with @Entity to indicate that it is a persistent entity.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Entity
@Table(name = "stories")
@Data
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long storyId;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserEntity user;

    @Column(name = "media_link", length = 255)
    private String mediaLink;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
