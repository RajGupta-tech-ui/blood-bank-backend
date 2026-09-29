package com.bloodbank.repository;

import com.bloodbank.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorIdOrderByDonationDateDesc(Long donorId);
}
