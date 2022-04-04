package ru.coderiders.BiteMeBee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.BiteMeBee.entity.BeeFamily;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {
}
