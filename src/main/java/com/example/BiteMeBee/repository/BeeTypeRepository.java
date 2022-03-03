package com.example.BiteMeBee.repository;

import com.example.BiteMeBee.entity.BeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeeTypeRepository extends JpaRepository<BeeType, Long> {

    Optional<BeeType> findByTitle(String title);
}
