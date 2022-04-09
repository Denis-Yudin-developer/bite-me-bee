package ru.coderiders.bitemebee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.coderiders.commons.superclass.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bee_families")
public class BeeFamily extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "bee_type_id", nullable = false)
    private BeeType beeType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hive_id")
    private Hive hive;
    @Column(name = "note", length = 1000, nullable = false)
    private String note;
    @Builder.Default
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
