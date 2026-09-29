package com.bloodbank.controller;

import com.bloodbank.model.*;
import com.bloodbank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;
    private final DonorRepository donorRepository;
    private final BloodInventoryRepository inventoryRepository;
    private final BloodRequestRepository requestRepository;
    private final DonationRepository donationRepository;

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalUsers", userRepository.count());
        result.put("totalDonors", donorRepository.count());
        result.put("totalInventoryRecords", inventoryRepository.count());
        result.put("totalRequests", requestRepository.count());
        result.put("pendingRequests",
                requestRepository.findByStatus(BloodRequest.RequestStatus.PENDING).size());
        result.put("totalDonations", donationRepository.count());
        result.put("bloodInventory", inventoryRepository.findAll());
        return result;
    }
}
