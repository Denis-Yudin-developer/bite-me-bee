package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.BeeFamily;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {
}
