package ru.coderiders.bitemebee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    @Column(name = "note", length = 1000)
    private String note;
    @Builder.Default
    @Column(name = "is_completed")
    private Boolean isCompleted = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hive_id")
    private Hive hive;

    //TODO добавить user_id

    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "closed_at")
    private Instant closedAt;
}
