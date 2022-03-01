package com.example.BiteMeBee.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table("hives")
@NoArgsConstructor
@AllArgsConstructor
public class Hive {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "family_id", nullable = false)
  private BeeFamily beeType;

  @Column(name="frame_count", nullable=false)
  private Integer frameCount;

}
