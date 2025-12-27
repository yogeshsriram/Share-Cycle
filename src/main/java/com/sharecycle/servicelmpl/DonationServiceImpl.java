package com.sharecycle.serviceImpl;

import com.sharecycle.entity.Donation;
import com.sharecycle.entity.DonationItem;
import com.sharecycle.entity.Status;
import com.sharecycle.repository.CategoryRepository;
import com.sharecycle.repository.DonationItemRepository;
import com.sharecycle.repository.DonationRepository;
import com.sharecycle.repository.UserRepository;
import com.sharecycle.service.DonationService;
import org.springframework.stereotype.Service;
import com.sharecycle.entity.Category;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationItemRepository donationItemRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public DonationServiceImpl(DonationItemRepository donationItemRepository,
                               CategoryRepository categoryRepository,
                               UserRepository userRepository) {
        this.donationItemRepository = donationItemRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DonationItem createDonation(DonationItem donationItem) {
        donationItem.setStatus(Status.AVAILABLE);
        return donationItemRepository.save(donationItem);
    }

    @Override
    public List<DonationItem> getAllDonations() {
        List<DonationItem> items = donationItemRepository.findAll();
        items.forEach(item -> {
            if (item.getExpiryDate() != null && item.getExpiryDate().isBefore(LocalDate.now())) {
                item.setStatus(Status.EXPIRED); // ✅ better than DECLINED
                donationItemRepository.save(item);
            }
        });
        return items;
    }

    @Override
    public List<DonationItem> filterDonations(String status, Long categoryId, String location) {
        List<DonationItem> items = donationItemRepository.findAll();
        return items.stream()
                .filter(item -> status == null || item.getStatus().name().equalsIgnoreCase(status))
                .filter(item -> categoryId == null || item.getCategory().getId().equals(categoryId))
                .filter(item -> location == null || item.getLocation().equalsIgnoreCase(location))
                .toList();
    }

    @Override
    public List<DonationItem> getDonationsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return donationItemRepository.findByCategory(category);
    }

    @Override
    public List<DonationItem> searchDonations(String query) {
        return donationItemRepository.findByItemNameContainingIgnoreCase(query);
    }

    @Override
    public DonationItem requestDonation(Long donationId) {
        DonationItem item = donationItemRepository.findById(donationId)
                .orElseThrow(() -> new IllegalArgumentException("Donation not found"));
        item.setStatus(Status.REQUESTED);
        return donationItemRepository.save(item);
    }

    @Override
    public DonationItem acceptDonation(Long donationId) {
        DonationItem item = donationItemRepository.findById(donationId)
                .orElseThrow(() -> new IllegalArgumentException("Donation not found"));
        item.setStatus(Status.ACCEPTED);
        return donationItemRepository.save(item);
    }

    @Override
    public DonationItem declineDonation(Long donationId) { // ✅ add this
        DonationItem item = donationItemRepository.findById(donationId)
                .orElseThrow(() -> new IllegalArgumentException("Donation not found"));
        item.setStatus(Status.DECLINED);
        return donationItemRepository.save(item);
    }
}