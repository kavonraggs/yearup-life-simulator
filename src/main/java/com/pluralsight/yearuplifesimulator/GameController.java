package com.pluralsight.yearuplifesimulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Random;

public class GameController {
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
        player = new Player(player.getName(), 0);
        balanceLabel.setText("Balance: $" + player.getBalance());
        eventLabel.setText("Welcome to Year Up Life Simulator!");
    }

    @FXML
    protected void onRollDice() {
        System.out.println("Roll button clicked");
    }

    @FXML
    private void onResetGame(){ System.out.println("Reset clicked"); }

    @FXML
    private void onViewLedger(){ System.out.println("View Ledger clicked"); }


    private Player player;
    private Random random = new Random();
}
