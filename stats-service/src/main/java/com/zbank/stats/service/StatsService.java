package com.zbank.stats.service;

import com.zbank.stats.model.ApplicationStats;
import com.zbank.stats.model.ApplicationSubmittedEvent;
import com.zbank.stats.repository.ApplicationStatsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatsService {

    private final ApplicationStatsRepository repository;

    public StatsService(ApplicationStatsRepository repository) {
        this.repository = repository;
    }

    public void handle(ApplicationSubmittedEvent event) {
        String date = LocalDate.now().toString();

        ApplicationStats stats = repository.findByApplicationDate(date)
                .orElseGet(() -> {
                    ApplicationStats newStats = new ApplicationStats();
                    newStats.setApplicationDate(date);
                    newStats.setTotalApplications(0);
                    newStats.setTotalIncome(0.0);
                    return newStats;
                });

        stats.setTotalApplications(stats.getTotalApplications() + 1);
        stats.setTotalIncome(stats.getTotalIncome() + event.getIncome());

        repository.save(stats);
    }
}
