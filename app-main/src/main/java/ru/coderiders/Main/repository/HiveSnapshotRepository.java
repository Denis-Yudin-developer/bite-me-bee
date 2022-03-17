package ru.coderiders.Main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.Main.entity.HiveSnapshot;

import java.time.Instant;
import java.util.List;

public interface HiveSnapshotRepository extends JpaRepository<HiveSnapshot, Long> {

    List<HiveSnapshot> findByCreatedAtBetweenAndHive_Id(Instant createdAtBegin, Instant createdAtEnd, Long id);
}
