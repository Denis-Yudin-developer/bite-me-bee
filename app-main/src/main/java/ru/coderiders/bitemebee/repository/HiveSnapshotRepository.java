package ru.coderiders.bitemebee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.HiveSnapshot;

import java.time.Instant;

public interface HiveSnapshotRepository extends JpaRepository<HiveSnapshot, Long> {
    Page<HiveSnapshot> findByCreatedAtBetweenAndHive_Id(Pageable pageable, Instant createdAtBegin, Instant createdAtEnd, Long id);
}
