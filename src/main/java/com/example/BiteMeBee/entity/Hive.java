package com.example.BiteMeBee.entity;


import lombok.*;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "family_id", nullable = false)
    private BeeFamily beeFamily;

    @Column(name = "frame_count", nullable = false)
    private Integer frameCount;

}
