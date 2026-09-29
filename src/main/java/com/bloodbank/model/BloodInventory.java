package com.bloodbank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blood_inventory",
       uniqueConstraints = @UniqueConstraint(columnNames = "blood_group"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BloodInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blood_group", nullable = false)
    private String bloodGroup;

    @Column(nullable = false)
    private Integer units;

    private String storageLocation;
}
