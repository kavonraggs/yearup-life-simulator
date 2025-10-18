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
        Transaction t = new Transaction(vendor, description, -Math.abs(amount));
        transactions.add(t);
        return t;
    }

    public ArrayList<Transaction> getAllTransactions(){
        return transactions;
    }

    public void printAllTransactions(){
        if (transactions.isEmpty()){
            System.out.println("No transactions yet. Keep playing to earn some dough!");
        } else {
            for (Transaction t: transactions){
                System.out.println(t);
            }
        }
    }

    public void resetLedger(){
        transactions.clear();
    }
}
