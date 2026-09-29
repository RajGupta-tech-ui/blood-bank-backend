package com.bloodbank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "donations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @Column(nullable = false)
    private String bloodGroup;

    @Column(nullable = false)
    private Integer units;

    @Builder.Default
    private LocalDate donationDate = LocalDate.now();

    private String notes;
}
