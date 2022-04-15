package ru.coderiders.bitemebee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family_snapshots")
public class BeeFamilySnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bee_family_id", nullable = false)
    private BeeFamily beeFamily;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "population_increase", nullable = false)
    private Integer populationIncrease;
    @Column(name = "drone_population_increase", nullable = false)
    private Integer dronePopulationIncrease;
    @Column(name = "worker_population_increase", nullable = false)
    private Integer workerPopulationIncrease;
    @Column(name = "queen_population_increase", nullable = false)
    private Integer queenPopulationIncrease;
    @Column(name = "activity", nullable = false)
    private Double activity;
    @Column(name = "mood", nullable = false)
    private Double mood;
}
