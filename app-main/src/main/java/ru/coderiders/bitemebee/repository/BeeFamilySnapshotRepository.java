package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;

import java.time.Instant;
import java.util.List;

public interface BeeFamilySnapshotRepository extends JpaRepository<BeeFamilySnapshot, Long> {

    List<BeeFamilySnapshot> findByCreatedAtBetweenAndBeeFamily_Id(Instant createdAtBegin, Instant createdAtEnd, Long id);
}
