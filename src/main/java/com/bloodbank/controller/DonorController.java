package com.bloodbank.controller;

import com.bloodbank.model.Donor;
import com.bloodbank.service.DonorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
@RequiredArgsConstructor
public class DonorController {
    private final DonorService service;

    @PostMapping
    public Donor create(@RequestBody Donor donor) {
        return service.create(donor);
    }

    @GetMapping
    public List<Donor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Donor getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/blood-group/{group}")
    public List<Donor> byBloodGroup(@PathVariable String group) {
        return service.byBloodGroup(group);
    }

    @GetMapping("/eligible")
    public List<Donor> eligible() {
        return service.eligibleDonors();
    }

    @PutMapping("/{id}")
    public Donor update(@PathVariable Long id, @RequestBody Donor donor) {
        return service.update(id, donor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
