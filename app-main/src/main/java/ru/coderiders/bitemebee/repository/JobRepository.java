package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.coderiders.bitemebee.entity.Job;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
        @Query("SELECT j FROM Job j WHERE j.hive.id = ?1 AND j.activity.id = ?2 AND j.isCompleted = false")
        Optional<Job> findByCompletedJobs(Long hiveId, Long activityId);

        @Query(value = "SELECT * FROM jobs j" +
                " WHERE j.hive_id = ?1" +
                " AND j.activity_id = ?2" +
                " AND j.is_completed = true" +
                " ORDER BY j.closed_at DESC LIMIT 1",
                nativeQuery = true)
        Optional<Job> findLastJob(Long hiveId, Long activityId);
}
