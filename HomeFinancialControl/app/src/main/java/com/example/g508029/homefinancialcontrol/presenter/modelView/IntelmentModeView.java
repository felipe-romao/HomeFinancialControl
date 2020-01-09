package com.example.g508029.homefinancialcontrol.presenter.modelView;

public class IntelmentModeView {
    private String id;
    private String date;
    private String description;
    private String value;

    public IntelmentModeView(){}

    public IntelmentModeView(String id, String date, String description, String value) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
