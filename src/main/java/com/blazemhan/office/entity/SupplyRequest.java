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
    private SupplyItem item;


    private Integer requestedQuantity;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "linemanager_id")
    private User approvedBy;

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public SupplyItem getItem() {
        return item;
    }

    public void setItem(SupplyItem item) {
        this.item = item;
    }

    public  Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity( Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public User getReleasedBy() {
        return releasedBy;
    }

    public void setReleasedBy(User releasedBy) {
        this.releasedBy = releasedBy;
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

    @ManyToOne
    @JoinColumn(name = "storekeeper_id")
    private User releasedBy;

    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime releasedAt;

}
