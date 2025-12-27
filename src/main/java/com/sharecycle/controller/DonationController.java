package com.sharecycle.controller;

import com.sharecycle.entity.Category;
import com.sharecycle.entity.DonationItem;
import com.sharecycle.entity.Status;
import com.sharecycle.entity.User;
import com.sharecycle.repository.CategoryRepository;
import com.sharecycle.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/donations")
public class DonationController {

    private final DonationService donationService;
    private final CategoryRepository categoryRepository;

    public DonationController(DonationService donationService, CategoryRepository categoryRepository) {
        this.donationService = donationService;
        this.categoryRepository = categoryRepository;
    }

    // ✅ Show form to create a new donation for a donor
    @GetMapping("/add/{donorId}")
    public String showAddDonationForm(@PathVariable Long donorId, Model model) {
        model.addAttribute("donationItem", new DonationItem());
        model.addAttribute("donorId", donorId);

        // fetch categories from DB
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "donation_form"; // Thymeleaf template
    }

    // ✅ Handle form submission
    @PostMapping("/add/{donorId}")
    public String addDonation(@PathVariable Long donorId,
                              @ModelAttribute("donationItem") DonationItem donationItem) {
        // link donor manually
        User donor = new User();
        donor.setId(donorId);
        donationItem.setDonor(donor);

        // fetch category by ID
        if (donationItem.getCategory() != null && donationItem.getCategory().getId() != null) {
            Category category = categoryRepository.findById(donationItem.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            donationItem.setCategory(category);
        }

        donationItem.setStatus(Status.AVAILABLE);
        donationService.createDonation(donationItem);

        return "redirect:/donations/dashboard/" + donorId;
    }

    // ✅ Show all donations (for testing / admin)
    @GetMapping("/list")
    public String listDonations(Model model) {
        List<DonationItem> donations = donationService.getAllDonations();
        model.addAttribute("donations", donations);
        return "donation_list";
    }

    // ✅ Donor dashboard
    @GetMapping("/dashboard/{donorId}")
    public String donorDashboard(@PathVariable Long donorId, Model model) {
        List<DonationItem> myDonations = donationService.getAllDonations().stream()
                .filter(d -> d.getDonor() != null && d.getDonor().getId().equals(donorId))
                .toList();

        model.addAttribute("donations", myDonations);
        model.addAttribute("donorId", donorId);

        return "donor_dashboard";
    }
}
