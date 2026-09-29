package com.bloodbank.service;

import com.bloodbank.exception.ResourceNotFoundException;
import com.bloodbank.model.Donor;
import com.bloodbank.repository.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonorService {
    private final DonorRepository donorRepository;

    public Donor create(Donor donor) {
        if (donorRepository.findByEmail(donor.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A donor with this email already exists");
        }
        return donorRepository.save(donor);
    }

    public List<Donor> getAll() {
        return donorRepository.findAll();
    }

    public Donor getById(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found"));
    }

    public List<Donor> byBloodGroup(String group) {
        return donorRepository.findByBloodGroupIgnoreCase(group);
    }

    public List<Donor> eligibleDonors() {
        return donorRepository.findByEligibleTrue();
    }

    public Donor update(Long id, Donor incoming) {
        Donor donor = getById(id);
        donor.setName(incoming.getName());
        donor.setPhone(incoming.getPhone());
        donor.setBloodGroup(incoming.getBloodGroup());
        donor.setAge(incoming.getAge());
        donor.setGender(incoming.getGender());
        donor.setCity(incoming.getCity());
        donor.setLastDonationDate(incoming.getLastDonationDate());
        donor.setEligible(incoming.isEligible());
        return donorRepository.save(donor);
    }

    public void delete(Long id) {
        if (!donorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Donor not found");
        }
        donorRepository.deleteById(id);
    }
}
