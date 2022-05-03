package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.coderiders.bitemebee.entity.BeeFamily;
import ru.coderiders.bitemebee.entity.BeeType;

import java.util.List;
import java.util.Optional;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {
    @Query("SELECT b FROM BeeFamily b WHERE b.beeType.id = ?1 AND b.isDeleted = false AND b.isAlive = true")
    List<BeeFamily> findByBeeTypeId(Long id);

    Optional<BeeFamily> findByHiveIdAndIsAliveTrue(Long hiveId);
}
