package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.coderiders.bitemebee.entity.BeeFamily;

import java.util.List;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {
    @Query("SELECT b FROM BeeFamily b WHERE b.beeType.id = ?1 AND b.isDeleted = false AND b.isAlive = true")
    List<BeeFamily> findByBeeTypeId(Long id);
}
