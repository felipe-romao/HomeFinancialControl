package com.example.g508029.homefinancialcontrol.model;

import java.util.Date;

public class Instalment {

    private int id;
    private double value;
    private String description;
    private String sequence;
    private Date date;

    public Instalment(int id, double value, String description, String sequence, Date date) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.sequence    = sequence;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getSequence() {
        return sequence;
    }

    public Date getDate() {
        return date;
    }
}
