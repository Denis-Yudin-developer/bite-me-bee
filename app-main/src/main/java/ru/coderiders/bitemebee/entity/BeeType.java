package ru.coderiders.bitemebee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.coderiders.commons.superclass.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bee_types")
public class BeeType extends BaseEntity {
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "min_co2", nullable = false)
    private Integer minCo2;
    @Column(name = "max_co2", nullable = false)
    private Integer maxCo2;
    @Column(name = "min_humidity", nullable = false)
    private Integer minHumidity;
    @Column(name = "max_humidity", nullable = false)
    private Integer maxHumidity;
    @Column(name = "min_temperature", nullable = false)
    private Integer minTemperature;
    @Column(name = "max_temperature", nullable = false)
    private Integer maxTemperature;
    @Column(name = "cold_resistance", nullable = false)
    private Double coldResistance;
    @Column(name = "disease_resistance", nullable = false)
    private Double diseaseResistance;
    @Column(name = "honey_productivity", nullable = false)
    private Double honeyProductivity;
    @Column(name = "egg_productivity", nullable = false)
    private Double eggProductivity;
    @Column(name = "aggression_level", nullable = false)
    private Double aggressionLevel;
    @Column(name = "roiling_level", nullable = false)
    private Double roilingLevel;
    @OneToMany(mappedBy = "beeType", fetch = FetchType.EAGER)
    private List<Schedule> schedules;

    public BeeType(Long id) {
        this.id = id;
    }
}
