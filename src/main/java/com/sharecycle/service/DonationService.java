package com.sharecycle.service;

import com.sharecycle.entity.DonationItem;
import java.util.List;

public interface DonationService {
    DonationItem createDonation(DonationItem donationItem);
    List<DonationItem> getAllDonations();
    List<DonationItem> filterDonations(String status, Long categoryId, String location);
    List<DonationItem> getDonationsByCategory(Long categoryId);
    List<DonationItem> searchDonations(String query);
    DonationItem requestDonation(Long donationId);
    DonationItem acceptDonation(Long donationId);
    DonationItem declineDonation(Long donationId);
}