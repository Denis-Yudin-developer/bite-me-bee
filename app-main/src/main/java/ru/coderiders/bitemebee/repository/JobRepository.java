package ru.coderiders.bitemebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.coderiders.bitemebee.entity.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
        @Query("SELECT j FROM Job j WHERE j.hive.id = ?1 AND j.activity.id = ?2 AND j.isCompleted = false")
        List<Job> findByCompletedJobs(Long hiveId, Long activityId);

        Job findByHiveIdAndActivity_Id(Long hiveId, Long activityId);
}
