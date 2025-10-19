package com.pluralsight.yearuplifesimulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Random;

public class GameController {
    private Player player;
    private Random random = new Random();
    private EventManager manager = new EventManager();
    private LifeEvent event;

    @FXML
    private Label welcomeText;
    @FXML
    private Button rollButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button ledgerButton;
    @FXML
    private Label eventLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;

    @FXML
    public void initialize(){
        player = new Player("Player1", 0);
        balanceLabel.setText("Current Balance: $" + player.getBalance());
        eventLabel.setText("Welcome to Year Up Life Simulator!");
    }

    @FXML
    protected void onRollDice() {
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        int diceTotal = dice1 + dice2;



        event = manager.getRandomEvent(diceTotal);
        eventLabel.setText(event.getMessage());
        if (event.getType() == LifeEvent.EventType.DEPOSIT) {
            player.deposit(event.getVendor(), event.getDescription(), event.getAmount());
        } else {
            player.spend(event.getVendor(), event.getDescription(), event.getAmount());
        }
        balanceLabel.setText("Current Balance: $" + player.getBalance());
    }

    @FXML
    private void onResetGame(){ System.out.println("Reset clicked"); }

    @FXML
    private void onViewLedger(){ System.out.println("View Ledger clicked"); }

}
