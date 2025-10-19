package com.pluralsight.yearuplifesimulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public class GameController {
    private Player player;
    private final Random random = new Random();
    private final EventManager manager = new EventManager();
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
    private ImageView dice1View;
    @FXML
    private ImageView dice2View;

    public GameController() {

    }


    @FXML
    public void initialize(){
        player = new Player("Player1", 0);
        balanceLabel.setText("Current Balance: $" + player.getBalance());
        eventLabel.setText("Welcome to Year Up Life Simulator!");

        String startFile = Objects.requireNonNull(getClass().getResource("/images/Dice1.png")).toExternalForm();
        Image startImage = new Image(startFile);
        dice1View.setImage(startImage);
        dice2View.setImage(startImage);

    }

    public void handleLuckyRoll(int diceTotal){
        String[] options = {"Over", "Under"};

        int choice = JOptionPane.showOptionDialog(null,
                "You rolled a " + diceTotal + "! Pick whether your next roll will be over or under 6 and if you guess correctly, you'll get a very special surprise!",
                "It's your lucky day!", 3, 3,
                null,
                options,
                null);

        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        String diceFile1 = Objects.requireNonNull(getClass().getResource("/images/Dice" + dice1 + ".png")).toExternalForm();
        String diceFile2 = Objects.requireNonNull(getClass().getResource("/images/Dice" + dice2 + ".png")).toExternalForm();
        Image diceImage1 = new Image(diceFile1);
        Image diceImage2 = new Image(diceFile2);
        dice1View.setImage(diceImage1);
        dice2View.setImage(diceImage2);

        diceTotal = dice1 + dice2;

        if (choice == 0 && diceTotal > 6) {
            JOptionPane.showMessageDialog(null ,"Where my girls at?! You guessed correctly!");
            player.deposit(event.getVendor(), event.getDescription(), event.getAmount());

        } else if (choice == 1 && diceTotal < 7) {
            JOptionPane.showMessageDialog(null ,"Where my girls at?! You guessed correctly!");
            player.deposit(event.getVendor(), event.getDescription(), event.getAmount());

        } else {
            JOptionPane.showMessageDialog(null,"You woke up and it was just a dream! No money for you this time.");
        }

    }

    @FXML
    protected void onRollDice() {
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        String diceFile1 = Objects.requireNonNull(getClass().getResource("/images/Dice" + dice1 + ".png")).toExternalForm();
        String diceFile2 = Objects.requireNonNull(getClass().getResource("/images/Dice" + dice2 + ".png")).toExternalForm();
        Image diceImage1 = new Image(diceFile1);
        Image diceImage2 = new Image(diceFile2);
        dice1View.setImage(diceImage1);
        dice2View.setImage(diceImage2);

        int diceTotal = dice1 + dice2;

        event = manager.getRandomEvent(diceTotal);
        eventLabel.setText(event.getMessage());



        if (diceTotal == 7 || diceTotal == 11) {
            handleLuckyRoll(diceTotal);
        } else {
            if (event.getType() == LifeEvent.EventType.DEPOSIT) {
                player.deposit(event.getVendor(), event.getDescription(), event.getAmount());
            } else {
                player.spend(event.getVendor(), event.getDescription(), event.getAmount());
            }
        }
        balanceLabel.setText("Current Balance: $" + player.getBalance());

    }

    @FXML
    private void onResetGame(){ System.out.println("Reset clicked"); }

    @FXML
    private void onViewLedger(){ System.out.println("View Ledger clicked"); }

}
