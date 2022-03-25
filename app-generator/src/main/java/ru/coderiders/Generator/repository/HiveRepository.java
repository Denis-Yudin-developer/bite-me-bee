package ru.coderiders.Generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.Generator.entity.Hive;

public interface HiveRepository extends JpaRepository<Hive, Long> {
}
