package ru.coderiders.generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.generator.entity.BeeFamily;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {
}
