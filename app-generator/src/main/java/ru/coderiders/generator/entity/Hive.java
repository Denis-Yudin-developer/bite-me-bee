package ru.coderiders.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "generator_hives")
public class Hive {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "bee_family_id")
    private BeeFamily beeFamily;
    @Builder.Default
    @Column(name = "is_overheated", nullable = false)
    private Boolean isOverheated = false;
    @Builder.Default
    @Column(name = "current_honey_amount", nullable = false)
    private Double currentHoneyAmount = 0D;
    @Column(name = "honey_capacity", nullable = false)
    private Double honeyCapacity;
    @Builder.Default
    @Column(name = "delta", nullable = false)
    private Double delta = 10D;
}
