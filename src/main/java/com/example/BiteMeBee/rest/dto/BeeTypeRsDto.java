package com.example.BiteMeBee.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BeeTypeRsDto {

  private long id;

  private String title;

  private String description;

  private int conditionsCo2;

  private int conditionsHumidity;

  private int conditionsTemperature;

  private double coldResistance;

  private double diseaseResistance;

  private double honeyProductivity;

  private double eggProductivity;

  private double aggression;

  private double roiling;
}
