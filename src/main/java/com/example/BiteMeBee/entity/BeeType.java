package com.example.BiteMeBee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
@Table("bee_types")
@NoArgsConstructor
@AllArgsConstructor
public class BeeType {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(name="title", nullable=false, unique=true)
  private String title;

  @Column(name="description", nullable=false, columnDefinition="TEXT")
  private String description;

  @Column(name="min_co2", nullable=false)
  private Integer minCo2;

  @Column(name="max_co2", nullable=false)
  private Integer maxCo2;

  @Column(name="min_humidity", nullable=false)
  private Integer min_Humidity;

  @Column(name="max_humidity", nullable=false)
  private Integer max_Humidity;

  @Column(name="min_temperature", nullable=false)
  private Integer minTemperature;

  @Column(name="max_temperature", nullable=false)
  private Integer maxTemperature;

  @Column(name="cold_resistance", nullable=false)
  private Double coldResistance;

  @Column(name="disease_resistance", nullable=false)
  private Double diseaseResistance;

  @Column(name="honey_productivity", nullable=false)
  private Double honeyProductivity;

  @Column(name="egg_productivity", nullable=false)
  private Double eggProductivity;

  @Column(name="aggression", nullable=false)
  private Double aggression;

  @Column(name="roiling", nullable=false)
  private Double roiling;

}
