package com.bloodbank.service;

import com.bloodbank.exception.ResourceNotFoundException;
import com.bloodbank.model.BloodInventory;
import com.bloodbank.repository.BloodInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodInventoryService {
    private final BloodInventoryRepository repository;

    public List<BloodInventory> getAll() {
        return repository.findAll();
    }

    public BloodInventory getByGroup(String group) {
        return repository.findByBloodGroupIgnoreCase(group)
                .orElseThrow(() -> new ResourceNotFoundException("Blood group not found in inventory"));
    }

    public BloodInventory save(BloodInventory inventory) {
        return repository.findByBloodGroupIgnoreCase(inventory.getBloodGroup())
                .map(existing -> {
                    existing.setUnits(inventory.getUnits());
                    existing.setStorageLocation(inventory.getStorageLocation());
                    return repository.save(existing);
                })
                .orElseGet(() -> repository.save(inventory));
    }

    public BloodInventory addUnits(String group, int units) {
        if (units <= 0) throw new IllegalArgumentException("Units must be greater than zero");
        BloodInventory inventory = repository.findByBloodGroupIgnoreCase(group)
                .orElseGet(() -> BloodInventory.builder()
                        .bloodGroup(group.toUpperCase())
                        .units(0)
                        .build());
        inventory.setUnits(inventory.getUnits() + units);
        return repository.save(inventory);
    }

    public BloodInventory removeUnits(String group, int units) {
        if (units <= 0) throw new IllegalArgumentException("Units must be greater than zero");
        BloodInventory inventory = getByGroup(group);
        if (inventory.getUnits() < units) {
            throw new IllegalArgumentException("Insufficient blood stock");
        }
        inventory.setUnits(inventory.getUnits() - units);
        return repository.save(inventory);
    }
}
