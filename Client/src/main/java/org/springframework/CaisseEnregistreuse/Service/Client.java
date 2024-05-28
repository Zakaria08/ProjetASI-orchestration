package org.springframework.CaisseEnregistreuse.Service;

import java.util.List;

public class Client {
    private String lastname;
    private String firstname;
    private String gender;
    private String birthday;
    private String cardnumber;
    private List<Ticket> receipts; // Ajouter cette ligne


    // Getters and setters
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }
    public List<Ticket> getReceipts() { return receipts; }
    public void setReceipts(List<Ticket> receipts) { this.receipts = receipts; }
}