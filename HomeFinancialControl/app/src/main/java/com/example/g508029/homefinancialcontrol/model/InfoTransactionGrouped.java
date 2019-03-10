package com.example.g508029.homefinancialcontrol.model;

public class InfoTransactionGrouped {
    private String description;
    private int quantity = 0;
    private Double total = 0.0;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
