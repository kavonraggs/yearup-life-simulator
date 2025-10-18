package com.pluralsight.yearuplifesimulator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
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

        try (InputStream input = getClass().getResourceAsStream("events.json")){
            if (input == null) {
                throw new IllegalStateException("event.json not found");
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(input);
            System.out.println("File loaded successfully");

            JsonNode badArray = root.get("bad");
            for (JsonNode node: badArray) {
                String vendor = node.get("vendor").asText();
                String description = node.get("description").asText();
                double amount = node.get("amount").asDouble();
                String typeString = node.get("type").asText();
                String message = node.get("message").asText();

                LifeEvent.EventType type = LifeEvent.EventType.valueOf(typeString.toUpperCase());
                LifeEvent event = new LifeEvent(message, vendor, description, amount, type);
                badEvents.add(event);
            }

            JsonNode neutralArray = root.get("neutral");
            for (JsonNode node: neutralArray) {
                String vendor = node.get("vendor").asText();
                String description = node.get("description").asText();
                double amount = node.get("amount").asDouble();
                String typeString = node.get("type").asText();
                String message = node.get("message").asText();

                LifeEvent.EventType type = LifeEvent.EventType.valueOf(typeString.toUpperCase());
                LifeEvent event = new LifeEvent(message, vendor, description, amount, type);
                neutralEvents.add(event);
            }

            JsonNode goodArray = root.get("good");
            for (JsonNode node: goodArray) {
                String vendor = node.get("vendor").asText();
                String description = node.get("description").asText();
                double amount = node.get("amount").asDouble();
                String typeString = node.get("type").asText();
                String message = node.get("message").asText();

                LifeEvent.EventType type = LifeEvent.EventType.valueOf(typeString.toUpperCase());
                LifeEvent event = new LifeEvent(message, vendor, description, amount, type);
                goodEvents.add(event);
            }

            JsonNode luckyArray = root.get("lucky");
            for (JsonNode node: luckyArray) {
                String vendor = node.get("vendor").asText();
                String description = node.get("description").asText();
                double amount = node.get("amount").asDouble();
                String typeString = node.get("type").asText();
                String message = node.get("message").asText();

                LifeEvent.EventType type = LifeEvent.EventType.valueOf(typeString.toUpperCase());
                LifeEvent event = new LifeEvent(message, vendor, description, amount, type);
                luckyEvents.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public LifeEvent getRandomEvent(int diceTotal){
        if (diceTotal <= 4) {
            return badEvents.get(random.nextInt(badEvents.size()));
        } else if (diceTotal <= 6) {
            return neutralEvents.get(random.nextInt(neutralEvents.size()));

        } else if (diceTotal == 7 || diceTotal == 11){
            return luckyEvents.get(random.nextInt(luckyEvents.size()));
        }else {
            return goodEvents.get(random.nextInt(goodEvents.size()));

        }
    }

}
