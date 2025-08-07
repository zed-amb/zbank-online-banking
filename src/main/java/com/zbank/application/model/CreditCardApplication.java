package com.zbank.application.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String ssn;
    private LocalDate dob;
    private String address;
    private double income;
    private LocalDate submittedAt = LocalDate.now();
}
