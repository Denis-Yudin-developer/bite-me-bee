package ru.coderiders.Main.repository;

import ru.coderiders.Main.entity.BeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeeTypeRepository extends JpaRepository<BeeType, Long> {

    Optional<BeeType> findByTitle(String title);
}
