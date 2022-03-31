package ru.coderiders.BiteMeBee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bee_families")
public class BeeFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bee_type_id", nullable = false)
    private BeeType beeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hive_id")
    private Hive hive;

    @Column(name = "note", length = 1000, nullable = false)
    private String note;

    @Column(name = "is_alive", nullable = false)
    private Boolean isAlive = true;

    @Column(name = "drone_population", nullable = false)
    private Long dronePopulation;

    @Column(name = "worker_population", nullable = false)
    private Long workerPopulation;

    @Column(name = "queen_population", nullable = false)
    private Long queenPopulation;

    @Column(name = "population", nullable = false)
    private Long population;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}
