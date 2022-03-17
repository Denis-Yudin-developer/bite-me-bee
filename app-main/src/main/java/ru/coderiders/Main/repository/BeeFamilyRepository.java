package ru.coderiders.Main.repository;

import ru.coderiders.Main.entity.BeeFamily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {

}
