package com.example.g508029.homefinancialcontrol.presenter.modelView;

import java.io.Serializable;

public class TransactionModelView implements Serializable {
    private String id;
    private String paymentMode;
    private String date;
    private String description;
    private String category;
    private String value;
    private String type;
    private String typeSymbol;
    private String frequency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeSymbol() {
        return typeSymbol;
    }

    public void setTypeSymbol(String typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
