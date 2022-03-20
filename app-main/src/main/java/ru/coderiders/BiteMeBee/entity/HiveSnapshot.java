package ru.coderiders.BiteMeBee.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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