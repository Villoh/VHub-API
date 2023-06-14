package dev.mikel_v.vhub_api.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Revly Entity.
 *
 * This class represents a Revly entity in the application.
 * It is mapped to the "revlyes" table in the database.
 *
 * Note: This class is annotated with @Entity to indicate that it is a persistent entity.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Entity
@Table(name = "revlyes")
@Data
public class RevlyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revly_id")
    private Long revlyId;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "fk_vly", nullable = false)
    private VlyEntity vly;
}
