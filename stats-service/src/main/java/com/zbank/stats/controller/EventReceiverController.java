package com.zbank.stats.controller;

import com.zbank.stats.model.ApplicationStats;
import com.zbank.stats.model.ApplicationSubmittedEvent;
import com.zbank.stats.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/events")
public class EventReceiverController {

    private final StatsService statsService;

    public EventReceiverController(StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/application-submitted")
    public String handleEvent(@RequestBody ApplicationSubmittedEvent event) {
        statsService.handle(event);
        return "Event processed";
    }

}
