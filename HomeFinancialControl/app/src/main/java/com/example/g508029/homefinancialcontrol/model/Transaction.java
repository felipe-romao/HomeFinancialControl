package com.example.g508029.homefinancialcontrol.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Transaction implements Serializable {
    private String id;
    private String type;
    private String description;
    private double value;
    private Date date;
    private String category;
    private String frequency;
    private String paymentMode;

    public Transaction(String type){
        this.id = UUID.randomUUID().toString();
        this.type           = type;
        this.value          = 0.0;
        this.description    = "";
        this.date           = Calendar.getInstance().getTime();;
        this.category       = "";
        this.paymentMode    = "";
    }

    public Transaction(String id, String type, String description, double value, Date date, String category, String paymentMode, String frequency){
        this.id             = id;
        this.type           = type;
        this.description    = description;
        this.value          = value;
        this.date           = date;
        this.category       = category;
        this.paymentMode    = paymentMode;
        this.frequency      = frequency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return getCategory();
    }
}
