package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.BeeType;

import java.util.Optional;

public interface BeeTypeRepository extends JpaRepository<BeeType, Long> {
    Optional<BeeType> findByTitle(String title);
}
