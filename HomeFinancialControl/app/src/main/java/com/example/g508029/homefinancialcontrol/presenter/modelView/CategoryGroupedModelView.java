package com.example.g508029.homefinancialcontrol.presenter.modelView;

public class CategoryGroupedModelView {
    private String count;
    private String description;
    private String value;

    public CategoryGroupedModelView(String count, String description, String value) {
        this.count = count;
        this.description = description;
        this.value = value;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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
