package com.zbank.application.repository;

import com.zbank.application.model.CreditCardApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardApplicationRepository extends JpaRepository<CreditCardApplication, Long> {
}
