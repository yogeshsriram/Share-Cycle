package com.sharecycle.controller;

import com.sharecycle.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/receiver")
public class ReceiverController {

    private final DonationService donationService;

    public ReceiverController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/dashboard/{receiverId}")
    public String receiverDashboard(@PathVariable Long receiverId, Model model) {
        model.addAttribute("donations", donationService.getAllDonations());
        return "receiver_dashboard"; // ✅ matches your template
    }
}