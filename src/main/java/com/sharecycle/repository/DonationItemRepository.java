package com.sharecycle.repository;

import com.sharecycle.entity.DonationItem;
import com.sharecycle.entity.Category;
import com.sharecycle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationItemRepository extends JpaRepository<DonationItem, Long> {
    List<DonationItem> findByCategory(Category category);
    List<DonationItem> findByDonor(User donor);
    List<DonationItem> findByItemNameContainingIgnoreCase(String query);
}