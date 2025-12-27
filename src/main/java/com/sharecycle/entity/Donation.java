package com.sharecycle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String category;
    private String description;
    private String location;

    private String expiryDate; // optional, only for food/medicine

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private User donor;   // link donation to donor
}