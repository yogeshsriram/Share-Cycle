package com.sharecycle.repository;

import com.sharecycle.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    // ✅ Correct way to query by receiver's id
    List<Request> findByReceiver_Id(Long receiverId);

    // ✅ Correct way to query by donation's id
    List<Request> findByDonation_Id(Long donationId);
}