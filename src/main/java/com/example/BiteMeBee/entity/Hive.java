package com.example.BiteMeBee.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "hives")
@NoArgsConstructor
@AllArgsConstructor
public class Hive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "family_id", nullable = false)
    private Set<BeeFamily> beeFamily;

    @Column(name = "frame_count", nullable = false)
    private Integer frameCount;

}
