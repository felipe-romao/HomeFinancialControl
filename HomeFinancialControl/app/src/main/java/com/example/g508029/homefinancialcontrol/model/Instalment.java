package com.example.g508029.homefinancialcontrol.model;

import java.util.Date;

public class Instalment {

    private int id;
    private double value;
    private String description;
    private String sequence;
    private Date date;
    private String category;

    public Instalment(int id, double value, String description, String sequence, Date date, String category) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.sequence    = sequence;
        this.date = date;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description + "(" + this.sequence + ")";
    }

    public String getSequence() {
        return sequence;
    }

    public Date getDate() {
        return date;
    }
}
