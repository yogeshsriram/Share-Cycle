package com.sharecycle.repository;

import com.sharecycle.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByCategory(String category);
    List<Donation> findByItemNameContainingIgnoreCase(String keyword);
}