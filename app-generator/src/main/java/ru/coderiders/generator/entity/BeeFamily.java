package ru.coderiders.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.coderiders.commons.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "generator_families")
public class BeeFamily extends BaseEntity {
    @Id
    private Long id;
    @Builder.Default
    @Column(name = "is_infected", nullable = false)
    private Boolean isInfected = false;
    @Column(name = "drone_population", nullable = false)
    private Long dronePopulation;
    @Column(name = "worker_population", nullable = false)
    private Long workerPopulation;
    @Column(name = "queen_population", nullable = false)
    private Long queenPopulation;
    @Column(name = "population", nullable = false)
    private Long population;
    @Column(name = "disease_resistance", nullable = false)
    private Double diseaseResistance;
    @Column(name = "honey_productivity", nullable = false)
    private Double honeyProductivity;
    @Column(name = "egg_productivity", nullable = false)
    private Double eggProductivity;
    @Builder.Default
    @Column(name = "activity", nullable = false)
    private Double activity = 1.0;
    @Builder.Default
    @Column(name = "mood", nullable = false)
    private Double mood = 1.0;
    @Builder.Default
    @Column(name = "delta", nullable = false)
    private Double delta = 1.0;

}
