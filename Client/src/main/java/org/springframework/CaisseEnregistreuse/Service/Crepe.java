package org.springframework.CaisseEnregistreuse.Service;

public class Crepe {
    private String purchasedItem;
    private int quantity;

    // Getters and Setters

    public String getPurchasedItem() {
        return purchasedItem;
    }

    public void setPurchasedItem(String purchasedItem) {
        this.purchasedItem = purchasedItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
