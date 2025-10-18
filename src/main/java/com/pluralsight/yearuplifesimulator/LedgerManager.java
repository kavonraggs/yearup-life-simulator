package com.pluralsight.yearuplifesimulator;

import java.util.ArrayList;

public class LedgerManager {
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Transaction addDeposit(String vendor, String description, double amount){
        Transaction t = new Transaction(vendor, description, amount);
        transactions.add(t);
        return t;
    }

    public Transaction addPayment(String vendor, String description, double amount){
        Transaction t = new Transaction(vendor, description, amount);
        transactions.add(t);
        return t;
    }

    public void getAllTransactions(){

    }

    public void printAllTransactions(){

    }


}
