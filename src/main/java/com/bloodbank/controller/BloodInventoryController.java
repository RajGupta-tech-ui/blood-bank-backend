package com.bloodbank.controller;

import com.bloodbank.model.BloodInventory;
import com.bloodbank.service.BloodInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class BloodInventoryController {
    private final BloodInventoryService service;

    @GetMapping
    public List<BloodInventory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{group}")
    public BloodInventory getByGroup(@PathVariable String group) {
        return service.getByGroup(group);
    }

    @PostMapping
    public BloodInventory save(@RequestBody BloodInventory inventory) {
        return service.save(inventory);
    }

    @PatchMapping("/{group}/add/{units}")
    public BloodInventory addUnits(@PathVariable String group, @PathVariable int units) {
        return service.addUnits(group, units);
    }

    @PatchMapping("/{group}/remove/{units}")
    public BloodInventory removeUnits(@PathVariable String group, @PathVariable int units) {
        return service.removeUnits(group, units);
    }
}
