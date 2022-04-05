package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.Hive;

import java.util.Optional;

public interface HiveRepository extends JpaRepository<Hive, Long> {
    Optional<Hive> findByName(String name);
}
