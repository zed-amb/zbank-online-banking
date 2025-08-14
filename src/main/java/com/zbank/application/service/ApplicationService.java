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
import java.util.List;
import java.util.NoSuchElementException;

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

    // service/ApplicationService.java
    public List<CreditCardApplication> findAll() {
        return repository.findAll();
    }

    public CreditCardApplication findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Application " + id + " not found"));
    }

    public CreditCardApplication update(Long id, ApplicationRequest req) {
        var app = findById(id);
        app.setFullName(req.fullName);
        app.setSsn(req.ssn);
        app.setDob(LocalDate.parse(req.dob));
        app.setAddress(req.address);
        app.setIncome(req.income);
        return repository.save(app);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new NoSuchElementException("Application " + id + " not found");
        repository.deleteById(id);
    }

}
