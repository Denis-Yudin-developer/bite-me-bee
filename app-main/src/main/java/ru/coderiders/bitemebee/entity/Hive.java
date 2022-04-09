package ru.coderiders.bitemebee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.coderiders.commons.superclass.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hives")
public class Hive extends BaseEntity {
    @OneToMany(mappedBy = "hive", fetch = FetchType.EAGER)
    private List<BeeFamily> beeFamilies;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "frame_count", nullable = false)
    private Integer frameCount;
    @Builder.Default
    @Column(name = "honey_amount", nullable = false)
    private Double honeyAmount = 0D;

    public Hive(Long id) {
        this.id = id;
    }
}
