package com.zbank.stats.model;

import lombok.Data;

@Data
public class ApplicationSubmittedEvent {
    private String applicationId;
    private String fullName;
    private double income;
    private String timestamp;
}
