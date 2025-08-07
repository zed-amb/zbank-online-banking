package com.zbank.application.controller;

import com.zbank.application.dto.ApplicationRequest;
import com.zbank.application.model.CreditCardApplication;
import com.zbank.application.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public CreditCardApplication submit(@RequestBody ApplicationRequest request) {
        return service.submitApplication(request);
    }
}