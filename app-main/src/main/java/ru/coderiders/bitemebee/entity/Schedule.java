package ru.coderiders.bitemebee.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bee_type_id", nullable = false)
    private BeeType beeType;
    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;
    @Column(name = "interval_in_minutes", nullable = false)
    private Long intervalInMinutes;
}
