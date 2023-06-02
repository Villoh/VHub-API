package dev.mikel_villota.vlrhub_api.entity;


import jakarta.persistence.*;
import lombok.Data;

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
