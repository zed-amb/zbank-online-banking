package com.zbank.stats.repository;

import com.zbank.stats.model.ApplicationStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationStatsRepository extends JpaRepository<ApplicationStats, Long> {
    Optional<ApplicationStats> findByApplicationDate(String applicationDate);
}
