package com.example.BiteMeBee.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bee_families")
public class BeeFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bee_type_id", nullable = false)
    private BeeType beeType;

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "is_alive", nullable = false)
    private Boolean isAlive;

}
