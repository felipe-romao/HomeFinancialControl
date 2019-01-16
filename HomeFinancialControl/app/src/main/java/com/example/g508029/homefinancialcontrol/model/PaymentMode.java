package com.example.g508029.homefinancialcontrol.model;

public class PaymentMode {
    private String id;
    private String mode;

    public PaymentMode(String id, String mode)
    {
        this.id     = id;
        this.mode   = mode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return this.getMode();
    }
}
