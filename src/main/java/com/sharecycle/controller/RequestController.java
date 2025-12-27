package com.sharecycle.controller;

import com.sharecycle.entity.DonationItem;
import com.sharecycle.entity.Request;
import com.sharecycle.service.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/create")
    public String createRequest(@RequestParam Long donationId, @RequestParam Long receiverId) {
        requestService.createRequest(donationId, receiverId);
        return "redirect:/requests/my/" + receiverId;
    }

    @GetMapping("/my/{receiverId}")
    public String myRequests(@PathVariable Long receiverId, Model model) {
        model.addAttribute("requests", requestService.getRequestsByReceiver(receiverId));
        return "receiver_requests"; // Thymeleaf template
    }

    @PostMapping("/accept/{requestId}")
    public String acceptRequest(@PathVariable Long requestId) {
        requestService.acceptRequest(requestId);
        return "redirect:/donations/list";
    }

    @PostMapping("/decline/{requestId}")
    public String declineRequest(@PathVariable Long requestId) {
        requestService.declineRequest(requestId);
        return "redirect:/donations/list";
    }
    @GetMapping("/donation/{donationId}")
    public String showRequestSlide(@PathVariable Long donationId, Model model) {
        // For demo: just grab the first request for this donation
        Request request = requestService.getRequestsForDonation(donationId).stream()
                .findFirst()
                .orElse(null);

        DonationItem donation = request != null ? request.getDonation() : null;

        model.addAttribute("request", request);
        model.addAttribute("donation", donation);

        return "donor_requests"; // shows the slide
    }

}