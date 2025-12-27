/* package com.sharecycle.controller;

import com.sharecycle.entity.DonationItem;
import com.sharecycle.service.DonationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")   // ✅ JSON endpoints live here
public class DonationRestController {

    private final DonationService donationService;

    public DonationRestController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/create")
    public DonationItem createDonation(@RequestBody DonationItem donationItem) {
        return donationService.createDonation(donationItem);
    }

    @GetMapping("/all")
    public List<DonationItem> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/category/{id}")
    public List<DonationItem> getDonationsByCategory(@PathVariable Long id) {
        return donationService.getDonationsByCategory(id);
    }

    @GetMapping("/search")
    public List<DonationItem> searchDonations(@RequestParam String query) {
        return donationService.searchDonations(query);
    }

    @PostMapping("/{id}/request")
    public DonationItem requestDonation(@PathVariable Long id) {
        return donationService.requestDonation(id);
    }

    @PostMapping("/{id}/accept")
    public DonationItem acceptDonation(@PathVariable Long id) {
        return donationService.acceptDonation(id);
    }

    @PostMapping("/{id}/decline")
    public DonationItem declineDonation(@PathVariable Long id) {
        return donationService.declineDonation(id);
    }

    @GetMapping("/mine/{donorId}")
    public List<DonationItem> getMyDonations(@PathVariable Long donorId) {
        return donationService.getDonationsByDonor(donorId);
    }

    @GetMapping("/filter")
    public List<DonationItem> filterDonations(@RequestParam(required = false) String status,
                                              @RequestParam(required = false) Long categoryId,
                                              @RequestParam(required = false) String location) {
        return donationService.filterDonations(status, categoryId, location);
    }
} */