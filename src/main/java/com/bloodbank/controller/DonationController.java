package com.bloodbank.controller;

import com.bloodbank.model.Donation;
import com.bloodbank.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService service;

    @PostMapping
    public Donation create(@RequestBody Donation donation) {
        return service.create(donation);
    }

    @GetMapping
    public List<Donation> getAll() {
        return service.getAll();
    }

    @GetMapping("/donor/{donorId}")
    public List<Donation> byDonor(@PathVariable Long donorId) {
        return service.byDonor(donorId);
    }
}
