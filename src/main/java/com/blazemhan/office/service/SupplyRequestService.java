package com.blazemhan.office.service;

import com.blazemhan.office.entity.*;
import com.blazemhan.office.exceptions.ApprovalException;
import com.blazemhan.office.exceptions.RejectionException;
import com.blazemhan.office.exceptions.RequestNotFoundException;
import com.blazemhan.office.repository.SupplyItemRepository;
import com.blazemhan.office.repository.SupplyRequestRepository;
import com.blazemhan.office.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SupplyRequestService {
        private final SupplyRequestRepository requestRepository;
        private final SupplyItemRepository itemRepository;
        private final UserRepository userRepository;

        public SupplyRequestService(SupplyRequestRepository requestRepository,
                                    SupplyItemRepository itemRepository,
                                    UserRepository userRepository) {
            this.requestRepository = requestRepository;
            this.itemRepository = itemRepository;
            this.userRepository = userRepository;
        }

        public SupplyRequest createRequest(Long requesterId, Long itemId, int quantity) {
            User requester = userRepository.findById(requesterId)
                    .orElseThrow(() -> new RuntimeException("Staff not found"));

            SupplyItem item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            if (quantity > item.getQuantity()) {
                throw new RuntimeException("Requested quantity exceeds stock");
            }

            SupplyRequest request = new SupplyRequest();
            request.setRequester(requester);
            request.setItem(item);
            request.setQuantityRequested(quantity);
            request.setStatus(RequestStatus.PENDING);
            request.setRequestedAt(LocalDateTime.now());
            return requestRepository.save(request);
        }

        public SupplyRequest approveRequest(Long requestId, Long lineManagerId) throws RequestNotFoundException, ApprovalException {
            SupplyRequest request = requestRepository.findById(requestId)
                    .orElseThrow(() -> new RequestNotFoundException("Invalid Id, Request Not Found"));
            User lineManager = userRepository.findById(lineManagerId)
                    .orElseThrow(() -> new RuntimeException("LineManager not found"));


            if (request.getStatus() != RequestStatus.PENDING) {
                throw new ApprovalException("Only PENDING requests can approved");
            }
            SupplyItem item = request.getItem();
            item.decreaseQuantity(request.getQuantityRequested());
            itemRepository.save(item);
            request.setLineManager(lineManager);
            request.setStatus(RequestStatus.APPROVED);
            request.setApprovedAt(LocalDateTime.now());


            return requestRepository.save(request);
        }

    @Transactional
    public SupplyRequest rejectRequest(Long requestId, Long lineManagerId, String reason) throws RequestNotFoundException, RejectionException {
        SupplyRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException("Invalid ID, Request not found"));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RejectionException("Only pending requests can be rejected");
        }

        User lineManager = userRepository.findById(lineManagerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        if (lineManager.getRole() != Role.MANAGER) {
            throw new RuntimeException("Only managers can reject requests");
        }

        request.setLineManager(lineManager);
        request.setStatus(RequestStatus.REJECTED);


        System.out.println("Request rejected: " + reason);

        return requestRepository.save(request);
    }

    @Transactional
    public SupplyRequest  releaseRequest(Long requestId, Long storekeeperId) throws RequestNotFoundException, ApprovalException {
            SupplyRequest supplyRequest = requestRepository.findById(requestId).orElseThrow(()
                    -> new RequestNotFoundException("Invalid ID,Request not found"));

             if(supplyRequest.getStatus() != RequestStatus.APPROVED){
                 throw new ApprovalException("Only APPROVED requests can be released");
             }

             User storekeeper = userRepository.findById(storekeeperId).orElseThrow(()->
                     new RuntimeException("Storekeeper not found"));

        if (storekeeper.getRole() != Role.STOREKEEPER) {
            throw new RuntimeException("Only storekeepers can release requests");
        }

        supplyRequest.setStoreKeeper(storekeeper);
        supplyRequest.setStatus(RequestStatus.RELEASED);
        supplyRequest.setReleasedAt(LocalDateTime.now());

        return requestRepository.save(supplyRequest);
    }
    }






