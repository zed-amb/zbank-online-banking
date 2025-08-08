package com.zbank.stats.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ApplicationStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicationDate;
    private int totalApplications;
    private double totalIncome;
}
