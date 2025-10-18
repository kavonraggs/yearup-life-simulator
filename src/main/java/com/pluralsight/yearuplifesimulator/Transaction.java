package com.pluralsight.yearuplifesimulator;

import java.util.ArrayList;

public class Transaction {
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private String vendor;
    private String description;
    private double amount;


    public Transaction( String vendor, String description, double amount){
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return vendor + " | " + description + " | " + String.format("$%.2f", amount);
    }

}
