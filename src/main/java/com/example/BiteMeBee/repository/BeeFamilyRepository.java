package com.example.BiteMeBee.repository;

import com.example.BiteMeBee.entity.BeeFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BeeFamilyRepository extends JpaRepository<BeeFamily, Long> {

}
