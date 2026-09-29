package com.bloodbank.service;

import com.bloodbank.exception.ResourceNotFoundException;
import com.bloodbank.model.BloodRequest;
import com.bloodbank.repository.BloodRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRequestService {
    private final BloodRequestRepository repository;
    private final BloodInventoryService inventoryService;

    public BloodRequest create(BloodRequest request) {
        request.setStatus(BloodRequest.RequestStatus.PENDING);
        return repository.save(request);
    }

    public List<BloodRequest> getAll() {
        return repository.findAllByOrderByRequestedAtDesc();
    }

    public BloodRequest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blood request not found"));
    }

    public BloodRequest updateStatus(Long id, BloodRequest.RequestStatus status) {
        BloodRequest request = getById(id);

        if (status == BloodRequest.RequestStatus.FULFILLED &&
                request.getStatus() != BloodRequest.RequestStatus.FULFILLED) {
            inventoryService.removeUnits(request.getBloodGroup(), request.getUnitsRequired());
        }

        request.setStatus(status);
        return repository.save(request);
    }
}
