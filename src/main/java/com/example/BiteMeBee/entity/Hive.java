package com.example.BiteMeBee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
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
    private List<BeeFamily> beeFamilies = Collections.emptyList();

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "frame_count", nullable = false)
    private Integer frameCount;

    @Column(name = "honey_amount", nullable = false)
    private Double honeyAmount = 0D;
}
