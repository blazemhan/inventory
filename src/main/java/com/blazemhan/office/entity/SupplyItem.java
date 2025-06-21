package com.blazemhan.office.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "supplyitems")

    public class SupplyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    private Integer quantity;
    private Integer threshold;


   public void decreaseQuantity(int number) {
      this.quantity = this.quantity - number;

    }

}

