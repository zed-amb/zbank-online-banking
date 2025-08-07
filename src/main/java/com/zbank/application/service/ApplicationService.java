package com.zbank.application.service;

import com.zbank.application.dto.ApplicationRequest;
import com.zbank.application.events.ApplicationSubmittedEvent;
import com.zbank.application.model.CreditCardApplication;
import com.zbank.application.repository.CreditCardApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    private final CreditCardApplicationRepository repository;
    private final EventPublisherService eventPublisher;

    public ApplicationService(CreditCardApplicationRepository repository,
                              EventPublisherService eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
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

        eventPublisher.publish(event, "com.zbank.application", "ApplicationSubmitted");

        return saved;
    }
}
