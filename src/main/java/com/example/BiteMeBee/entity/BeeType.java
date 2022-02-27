package com.example.BiteMeBee.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BeeType {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  @Column(name="title", nullable=false, unique=true)
  private String title;

  @Column(name="description", nullable=false)
  private String description;

  @Column(name="conditions_co2", nullable=false)
  private int conditionsCo2;

  @Column(name="conditions_humidity", nullable=false)
  private int conditionsHumidity;

  @Column(name="conditions_temperature", nullable=false)
  private int conditionsTemperature;

  @Column(name="cold_resistance", nullable=false)
  private double coldResistance;

  @Column(name="disease_resistance", nullable=false)
  private double diseaseResistance;

  @Column(name="honey_productivity", nullable=false)
  private double honeyProductivity;

  @Column(name="egg_productivity", nullable=false)
  private double eggProductivity;

  @Column(name="aggression", nullable=false)
  private double aggression;

  @Column(name="roiling", nullable=false)
  private double roiling;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    BeeType beeType = (BeeType) o;
    return Objects.equals(id, beeType.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
