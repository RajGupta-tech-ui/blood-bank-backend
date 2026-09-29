package com.bloodbank.repository;

import com.bloodbank.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    Optional<Donor> findByEmail(String email);
    List<Donor> findByBloodGroupIgnoreCase(String bloodGroup);
    List<Donor> findByEligibleTrue();
}
