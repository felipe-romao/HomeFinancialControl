package com.example.g508029.homefinancialcontrol.model;

public class CategoryGrouped {
    private String description;
    private int count = 0;
    private Double value = 0.0;

    public CategoryGrouped(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void increaseCount(){
        this.count++;
    }
    public void addValue(Double valueNew){
        this.value += valueNew;
    }
}
