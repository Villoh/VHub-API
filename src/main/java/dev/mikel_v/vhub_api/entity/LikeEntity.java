package dev.mikel_v.vhub_api.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Like Entity.
 *
 * This class represents a like entity in the application.
 * It is mapped to the "likes" table in the database.
 *
 * Note: This class is annotated with @Entity to indicate that it is a persistent entity.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Entity
@Table(name = "likes")
@Data
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "fk_vly")
    private VlyEntity vly;

    @ManyToOne
    @JoinColumn(name = "fk_moment")
    private MomentEntity moment;

    @PrePersist
    @PreUpdate
    private void validateConstraints() {
        if ((vly == null && moment == null) || (vly != null && moment != null)) {
            System.out.println(getVly().getVlyId());
            throw new IllegalStateException("Exactly one of fk_vly or fk_moment must be non-null.");
        }
    }
}
