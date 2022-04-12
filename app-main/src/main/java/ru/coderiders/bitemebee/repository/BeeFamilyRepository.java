package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.BeeFamily;

import java.util.List;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {
    List<BeeFamily> findByBeeTypeIdAndIsDeletedFalseAndIsAliveTrue(Long id);
}
