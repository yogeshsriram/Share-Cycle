/* package com.sharecycle.controller;

import com.sharecycle.entity.DonationItem;
import com.sharecycle.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/donations")   // ✅ Views live here
public class DonationViewController {

    private final DonationService donationService;

    public DonationViewController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/dashboard/{donorId}")
    public String donorDashboard(@PathVariable Long donorId, Model model) {
        List<DonationItem> donations = donationService.getDonationsByDonor(donorId);
        model.addAttribute("donations", donations);
        return "donor-dashboard"; // ✅ maps to donor_dashboard.html in templates/
    }
} */