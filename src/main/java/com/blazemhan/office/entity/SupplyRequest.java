package com.blazemhan.office.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "supplyrequests")


public class SupplyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "supply_item_id")
    private SupplyItem supplyItem;


    private Integer quantityRequested;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "linemanager_id")
    private User lineManager;

    @ManyToOne
    @JoinColumn(name = "storekeeper_id")
    private User storeKeeper;

    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime releasedAt;

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public SupplyItem getItem() {
        return supplyItem;
    }

    public void setItem(SupplyItem item) {
        this.supplyItem = item;
    }

    public Integer getQuantityRequested() {
        return quantityRequested;
    }

    public void setQuantityRequested(Integer quantityRequested) {
        this.quantityRequested = quantityRequested;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public User getLineManager() {
        return lineManager;
    }

    public void setLineManager(User lineManager) {
        this.lineManager = lineManager;
    }

    public User getStoreKeeper() {
        return storeKeeper;
    }

    public void setStoreKeeper(User storeKeeper) {
        this.storeKeeper = storeKeeper;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public LocalDateTime getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(LocalDateTime releasedAt) {
        this.releasedAt = releasedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
