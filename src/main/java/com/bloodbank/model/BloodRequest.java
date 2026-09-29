package com.bloodbank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "blood_requests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BloodRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private String bloodGroup;

    @Column(nullable = false)
    private Integer unitsRequired;

    private String hospitalName;
    private String contactNumber;
    private String urgency;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RequestStatus status = RequestStatus.PENDING;

    @Builder.Default
    private LocalDateTime requestedAt = LocalDateTime.now();

    public enum RequestStatus {
        PENDING, APPROVED, REJECTED, FULFILLED
    }
}
