package ru.coderiders.bitemebee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hives")
public class Hive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "hive", fetch = FetchType.EAGER)
    private List<BeeFamily> beeFamilies;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "frame_count", nullable = false)
    private Integer frameCount;
    @Builder.Default
    @Column(name = "honey_amount", nullable = false)
    private Double honeyAmount = 0D;
}
