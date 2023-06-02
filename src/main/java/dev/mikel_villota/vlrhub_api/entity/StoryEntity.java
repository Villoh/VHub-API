package dev.mikel_villota.vlrhub_api.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
