package dev.mikel_villota.vlrhub_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "follows")
@Data
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private UserEntity following;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;
}
