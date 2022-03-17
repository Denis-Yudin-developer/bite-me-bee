package ru.coderiders.Main.repository;

import ru.coderiders.Main.entity.Hive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiveRepository extends JpaRepository<Hive, Long> {
}
