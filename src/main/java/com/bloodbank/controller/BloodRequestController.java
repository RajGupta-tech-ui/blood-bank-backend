package com.bloodbank.controller;

import com.bloodbank.model.BloodRequest;
import com.bloodbank.service.BloodRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class BloodRequestController {
    private final BloodRequestService service;

    @PostMapping
    public BloodRequest create(@RequestBody BloodRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<BloodRequest> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BloodRequest getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}/status")
    public BloodRequest updateStatus(
            @PathVariable Long id,
            @RequestParam BloodRequest.RequestStatus status) {
        return service.updateStatus(id, status);
    }
}
