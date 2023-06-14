package dev.mikel_v.vhub_api.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Impression Entity.
 *
 * This class represents an impression entity in the application.
 * It is mapped to the "impressions" table in the database.
 *
 * Note: This class is annotated with @Entity to indicate that it is a persistent entity.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Entity
@Table(name = "impressions")
@Data
public class ImpressionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "impression_id")
    private Long impressionId;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "fk_vly")
    private VlyEntity vly;

    @ManyToOne
    @JoinColumn(name = "fk_moment")
    private MomentEntity moment;

    @ManyToOne
    @JoinColumn(name = "fk_story")
    private StoryEntity story;

    @PrePersist
    @PreUpdate
    private void validateConstraints() {
        boolean vlyNotNull = vly != null;
        boolean momentNotNull = moment != null;
        boolean storyNotNull = story != null;

        if (!((vlyNotNull && !momentNotNull && !storyNotNull) ||
                (!vlyNotNull && momentNotNull && !storyNotNull) ||
                (!vlyNotNull && !momentNotNull && storyNotNull))) {
            throw new IllegalStateException("Exactly one of fk_vly, fk_moment, or fk_story must be non-null.");
        }
    }
}
