package com.pluralsight.yearuplifesimulator;


public class LifeEvent {
    private final String vendor;
    private final String description;
    private final double amount;
    private final EventType type;
    public enum EventType { DEPOSIT, PAYMENT }


    public LifeEvent(String vendor, String description, double amount, EventType type){
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

}
