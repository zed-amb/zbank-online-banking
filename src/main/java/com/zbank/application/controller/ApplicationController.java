package com.zbank.application.controller;

import com.zbank.application.dto.ApplicationRequest;
import com.zbank.application.model.CreditCardApplication;
import com.zbank.application.service.ApplicationService;

import java.util.List;

import org.springframework.http.HttpStatus;
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

     @GetMapping
    public List<CreditCardApplication> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public CreditCardApplication one(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    public CreditCardApplication update(@PathVariable Long id, @RequestBody ApplicationRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}