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
@ToString
@Table(name = "hive_snapshots")
public class HiveSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "hive_id", nullable = false)
    private Hive hive;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "temperature", nullable = false)
    private Double temperature;
    @Column(name = "humidity", nullable = false)
    private Double humidity;
    @Column(name = "co2", nullable = false)
    private Double co2;
    @Column(name = "honey_increase", nullable = false)
    private Double honeyIncrease;
}