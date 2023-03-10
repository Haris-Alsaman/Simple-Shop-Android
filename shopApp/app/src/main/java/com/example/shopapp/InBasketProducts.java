package com.example.shopapp;

public class InBasketProducts {
    private String id;
    private int amount;

    public String getId() {
        return id;
    }

    public InBasketProducts(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
