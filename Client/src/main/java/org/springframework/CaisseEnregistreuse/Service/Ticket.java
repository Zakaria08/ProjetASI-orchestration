package org.springframework.CaisseEnregistreuse.Service;

public class Ticket {
    private int id;
    private int receiptNumber;
    private String purchaseDate;
    private String itemsPurchased;
    private int quantity;
    private double pricePerItem;
    private double totalAmount;
    private String paymentMethod;
    private String storeLocation;
    private String cashierName;
    private String cardNumber;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(int receiptNumber) { this.receiptNumber = receiptNumber; }
    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }
    public String getItemsPurchased() { return itemsPurchased; }
    public void setItemsPurchased(String itemsPurchased) { this.itemsPurchased = itemsPurchased; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPricePerItem() { return pricePerItem; }
    public void setPricePerItem(double pricePerItem) { this.pricePerItem = pricePerItem; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getStoreLocation() { return storeLocation; }
    public void setStoreLocation(String storeLocation) { this.storeLocation = storeLocation; }
    public String getCashierName() { return cashierName; }
    public void setCashierName(String cashierName) { this.cashierName = cashierName; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
}