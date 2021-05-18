package edu.ankital.hexagonal.items.application.model;

public class QualityCheckCommand {
    String productName;

    public QualityCheckCommand(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
