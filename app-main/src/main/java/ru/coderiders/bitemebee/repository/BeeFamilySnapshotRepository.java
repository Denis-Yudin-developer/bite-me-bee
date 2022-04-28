package ru.coderiders.bitemebee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;

import java.time.Instant;

public interface BeeFamilySnapshotRepository extends JpaRepository<BeeFamilySnapshot, Long> {

    Page<BeeFamilySnapshot> findByCreatedAtBetweenAndBeeFamily_Id(
            Pageable pageable, Instant createdAtBegin, Instant createdAtEnd, Long id);
}
