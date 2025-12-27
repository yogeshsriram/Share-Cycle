package com.sharecycle.service;

import com.sharecycle.entity.Request;
import java.util.List;

public interface RequestService {
    Request createRequest(Long donationId, Long receiverId);
    List<Request> getRequestsByReceiver(Long receiverId);
    List<Request> getRequestsForDonation(Long donationId);


    Request acceptRequest(Long requestId);
    Request declineRequest(Long requestId);
    Long getDonationIdByRequest(Long requestId);
}