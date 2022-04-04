package ru.coderiders.Generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.Generator.entity.Hive;

import java.util.Optional;

public interface HiveRepository extends JpaRepository<Hive, Long> {
    Optional<Hive> findByBeeFamilyId(Long id);
}
