package dev.mikel_v.vhub_api.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Moment Entity.
 *
 * This class represents a moment entity in the application.
 * It is mapped to the "moments" table in the database.
 *
 * Note: This class is annotated with @Entity to indicate that it is a persistent entity.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Entity
@Table(name = "moments")
@Data
public class MomentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moment_id")
    private Long momentId;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserEntity user;

    @Column(name = "media_link", nullable = false, length = 255)
    private String mediaLink;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
