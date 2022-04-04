package ru.coderiders.BiteMeBee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.BiteMeBee.entity.BeeType;

import java.util.Optional;

public interface BeeTypeRepository extends JpaRepository<BeeType, Long> {
    Optional<BeeType> findByTitle(String title);
}
