package ru.coderiders.bitemebee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.coderiders.bitemebee.entity.Schedule;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByBeeType_IdAndActivity_Id(Long beeTypeId, Long activityId);

    Page<Schedule> findByBeeType_Id(Long beeTypeId, Pageable pageable);
}
