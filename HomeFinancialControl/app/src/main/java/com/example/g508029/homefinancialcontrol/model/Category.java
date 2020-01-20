package com.example.g508029.homefinancialcontrol.model;

public class Category {
    private String id;
    private String description;
    private String typeMovement;
    private String frequency;

    public Category(String id, String description, String typeMovement, String frequency){
        this.id             = id;
        this.description    = description;
        this.typeMovement   = typeMovement;
        this.frequency      = frequency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeMovement() {
        return typeMovement;
    }

    public void setTypeMovement(String typeMovement) {
        this.typeMovement = typeMovement;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "[ " + this.getTypeMovement() + " ]   " + this.getDescription();
    }
}
