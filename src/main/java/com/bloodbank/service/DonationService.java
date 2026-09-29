package com.bloodbank.service;

import com.bloodbank.exception.ResourceNotFoundException;
import com.bloodbank.model.Donation;
import com.bloodbank.model.Donor;
import com.bloodbank.repository.DonationRepository;
import com.bloodbank.repository.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final BloodInventoryService inventoryService;

    public Donation create(Donation donation) {
        Donor donor = donorRepository.findById(donation.getDonor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found"));

        if (!donor.isEligible()) {
            throw new IllegalArgumentException("Donor is currently not eligible");
        }

        donation.setDonor(donor);
        donation.setBloodGroup(donor.getBloodGroup());
        donation.setDonationDate(LocalDate.now());

        Donation saved = donationRepository.save(donation);
        inventoryService.addUnits(donation.getBloodGroup(), donation.getUnits());

        donor.setLastDonationDate(LocalDate.now());
        donorRepository.save(donor);

        return saved;
    }

    public List<Donation> byDonor(Long donorId) {
        return donationRepository.findByDonorIdOrderByDonationDateDesc(donorId);
    }

    public List<Donation> getAll() {
        return donationRepository.findAll();
    }
}
