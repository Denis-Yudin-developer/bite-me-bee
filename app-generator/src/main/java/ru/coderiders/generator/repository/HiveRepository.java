package ru.coderiders.generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.generator.entity.Hive;

import java.util.List;
import java.util.Optional;

public interface HiveRepository extends JpaRepository<Hive, Long> {
    Optional<Hive> findByBeeFamilyId(Long id);

    List<Hive> findAllByBeeFamilyNotNull();
}
