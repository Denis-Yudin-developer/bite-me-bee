package ru.coderiders.BiteMeBee.repository;

import ru.coderiders.BiteMeBee.entity.Hive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HiveRepository extends JpaRepository<Hive, Long> {
    
    Optional<Hive> findByName(String name);
}
