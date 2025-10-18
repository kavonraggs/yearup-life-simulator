package com.pluralsight.yearuplifesimulator;

import java.util.ArrayList;
import java.util.Random;

public class EventManager {
    ArrayList<LifeEvent> goodEvents = new ArrayList<>();
    ArrayList<LifeEvent> neutralEvents = new ArrayList<>();
    ArrayList<LifeEvent> badEvents = new ArrayList<>();
    ArrayList<LifeEvent> luckyEvents = new ArrayList<>();
    Random random;

    public EventManager(){
        random = new Random();

    }



    public LifeEvent getRandomEvent(){


    }

}
