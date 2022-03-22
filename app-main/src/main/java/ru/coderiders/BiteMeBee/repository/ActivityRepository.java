package ru.coderiders.BiteMeBee.repository;

import ru.coderiders.BiteMeBee.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findByTitle(String title);
}
