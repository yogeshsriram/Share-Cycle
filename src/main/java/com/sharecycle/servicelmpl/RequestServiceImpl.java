package com.sharecycle.serviceImpl;

import com.sharecycle.entity.DonationItem;
import com.sharecycle.entity.Request;
import com.sharecycle.entity.Status;
import com.sharecycle.entity.User;
import com.sharecycle.repository.DonationItemRepository;
import com.sharecycle.repository.RequestRepository;
import com.sharecycle.repository.UserRepository;
import com.sharecycle.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final DonationItemRepository donationItemRepository;
    private final UserRepository userRepository;

    public RequestServiceImpl(RequestRepository requestRepository,
                              DonationItemRepository donationItemRepository,
                              UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.donationItemRepository = donationItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Request createRequest(Long donationId, Long receiverId) {
        DonationItem donation = donationItemRepository.findById(donationId)
                .orElseThrow(() -> new IllegalArgumentException("Donation not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        Request request = new Request();
        request.setDonation(donation);
        request.setReceiver(receiver);
        request.setStatus(Status.REQUESTED);

        return requestRepository.save(request);
    }

    @Override
    public List<Request> getRequestsByReceiver(Long receiverId) {
        return requestRepository.findByReceiver_Id(receiverId);
    }

    @Override
    public List<Request> getRequestsForDonation(Long donationId) {
        return requestRepository.findByDonation_Id(donationId);
    }

    @Override
    public Request acceptRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(Status.ACCEPTED);

        // ✅ also mark the donation as no longer available
        DonationItem donation = request.getDonation();
        donation.setStatus(Status.ACCEPTED);

        donationItemRepository.save(donation);
        return requestRepository.save(request);
    }

    @Override
    public Request declineRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(Status.DECLINED);

        // ✅ donation stays AVAILABLE, only request is declined
        return requestRepository.save(request);
    }

    @Override
    public Long getDonationIdByRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .map(r -> r.getDonation().getId())
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }
}
