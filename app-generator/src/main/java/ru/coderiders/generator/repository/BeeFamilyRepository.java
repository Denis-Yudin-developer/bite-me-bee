package ru.coderiders.Generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.Generator.entity.BeeFamily;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {
}
