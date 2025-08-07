package com.zbank.application.service;

import com.zbank.application.dto.ApplicationRequest;
import com.zbank.application.events.ApplicationSubmittedEvent;
import com.zbank.application.model.CreditCardApplication;
import com.zbank.application.repository.CreditCardApplicationRepository;
import com.zbank.application.utils.EventBridgePublisher;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    private final CreditCardApplicationRepository repository;

    public ApplicationService(CreditCardApplicationRepository repository) {
        this.repository = repository;
    }

    public CreditCardApplication submitApplication(ApplicationRequest request) {
        CreditCardApplication app = new CreditCardApplication();
        app.setFullName(request.fullName);
        app.setSsn(request.ssn);
        app.setDob(LocalDate.parse(request.dob));
        app.setAddress(request.address);
        app.setIncome(request.income);

        CreditCardApplication saved = repository.save(app);

        ApplicationSubmittedEvent event = new ApplicationSubmittedEvent();
        event.applicationId = saved.getId().toString();
        event.fullName = saved.getFullName();
        event.income = saved.getIncome();
        event.timestamp = Instant.now().toString();

        try {
            EventBridgePublisher.publish("com.zbank.application", "ApplicationSubmitted", event);
            logger.info("Published ApplicationSubmittedEvent for appId={}", event.applicationId);
        } catch (Exception e) {
            logger.error("Failed to publish ApplicationSubmittedEvent for appId={}: {}", event.applicationId, e.getMessage());
        }

        return saved;
    }
}
