package dev.mikel_villota.vlrhub_api.entity;


import jakarta.persistence.*;
import lombok.Data;

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
