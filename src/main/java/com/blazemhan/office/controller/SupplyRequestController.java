package com.blazemhan.office.controller;

import com.blazemhan.office.entity.SupplyRequest;
import com.blazemhan.office.entity.User;
import com.blazemhan.office.exceptions.ApprovalException;
import com.blazemhan.office.exceptions.RejectionException;
import com.blazemhan.office.exceptions.RequestNotFoundException;
import com.blazemhan.office.repository.UserRepository;
import com.blazemhan.office.service.SupplyRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/api/supplies")
    public class SupplyRequestController {

        private final SupplyRequestService requestService;
    private final UserRepository userRepository;

    public SupplyRequestController(SupplyRequestService requestService, UserRepository userRepository) {
            this.requestService = requestService;
        this.userRepository = userRepository;
    }

        @PostMapping("/request")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<?> createRequest(
                @RequestParam("itemId") Long itemId,
                @RequestParam("quantity") int quantity, UriComponentsBuilder builder) {

             String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByUsername(username);
            System.out.println(username);
            Long userId = user.getId();
            SupplyRequest createdRequest = requestService.createRequest(userId, itemId, quantity);
            URI location = builder.path("api/supplies/request/{id}").
                    buildAndExpand(createdRequest.getId())
                    .toUri();
            return ResponseEntity.created(location).body("Request created successfully");
        }

        @PostMapping("/approve")
        @PreAuthorize("hasRole('MANAGER')")
        public ResponseEntity<?> approveRequest(
                @RequestParam("requestId") Long requestId) throws RequestNotFoundException, ApprovalException {

             String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User manager = userRepository.findByUsername(username);
            Long managerId = manager.getId();

            SupplyRequest request = requestService.approveRequest(requestId, managerId);
            return ResponseEntity.ok("Request approved by " + request.getLineManager().getUsername());
        }

    @PostMapping("/reject")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> rejectRequest(
            @RequestParam Long requestId,
            @RequestParam String reason) throws RequestNotFoundException, RejectionException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User manager = userRepository.findByUsername(username);
        Long managerId = manager.getId();


        SupplyRequest request = requestService.rejectRequest(requestId, managerId, reason);
        return ResponseEntity.ok("Request rejected by " + request.getLineManager().getUsername());
    }

    @PostMapping("/release")
    @PreAuthorize("hasRole('STOREKEEPER')")
    public ResponseEntity<?> releaseRequest(@RequestParam("requestId") Long requestId) throws ApprovalException, RequestNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User stopKeeper = userRepository.findByUsername(username);
        Long storekeeperId = stopKeeper.getId();


        SupplyRequest request = requestService.releaseRequest(requestId, storekeeperId);

        return ResponseEntity.ok("Request released by " + request.getStoreKeeper().getUsername());

    }

    }


