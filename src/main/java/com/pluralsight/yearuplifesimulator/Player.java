package com.pluralsight.yearuplifesimulator;

public class Player {
    private final String name;
    private double balance;
    private final LedgerManager ledger;

    public Player(String name, double balance){
        this.name = name;
        this.balance = balance;
        this.ledger = new LedgerManager();
    }

    public Transaction deposit(String vendor, String description, double amount){
        balance += amount;
        return ledger.addDeposit(vendor, description, amount);
    }

    public Transaction spend(String vendor, String description, double amount){
        balance -= Math.abs(amount);
        return ledger.addPayment(vendor, description, amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void viewLedger(){
        ledger.printAllTransactions();
    }

    public void resetPlayer(){
        balance = 0;
        ledger.resetLedger();
    }

}
