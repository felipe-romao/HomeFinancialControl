package com.example.g508029.homefinancialcontrol.presenter.modelView;

public class TransactionModelView {
    private String category;
    private String value;
    private String type;
    private String typeSymbol;

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
}
