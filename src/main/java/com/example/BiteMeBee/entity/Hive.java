package com.example.BiteMeBee.entity;


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

    @OneToMany
    @JoinColumn(name = "family_id", nullable = false)
    private List<BeeFamily> beeFamily;

    @Column(name = "frame_count", nullable = false)
    private Integer frameCount;

}
