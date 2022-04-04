package ru.coderiders.Generator.entity;

import lombok.*;

import javax.persistence.*;

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
