package dev.mikel_villota.vlrhub_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "impression")
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
