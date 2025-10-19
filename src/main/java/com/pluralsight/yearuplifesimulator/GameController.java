package com.pluralsight.yearuplifesimulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public class GameController {
    public HBox diceBox;
    public Label titleLabel;
    public VBox statsBox;
    public HBox buttonBox;
    private Player player;
    private final Random random = new Random();
    private final EventManager manager = new EventManager();
    private LifeEvent event;


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
    @FXML
    private Label resultLabel;


    public GameController() {

    }


    @FXML
    public void initialize(){
        player = new Player("Player1", 2100);
        balanceLabel.setText("Current Balance: $" + player.getBalance());
        eventLabel.setText("Welcome! \nYou just received your first Year Up stipend.\nSpend it wisely!");

        String startFile = Objects.requireNonNull(getClass().getResource("/images/Dice1.png")).toExternalForm();
        Image startImage = new Image(startFile);
        dice1View.setImage(startImage);
        dice2View.setImage(startImage);

        resultLabel.setVisible(false);

    }

    boolean isLucky = false;
    public void handleLuckyRoll(int diceTotal){
        String[] options = {"Over", "Under"};

        int choice = JOptionPane.showOptionDialog(null,
                "You rolled a " + diceTotal + "! Pick whether your next roll will be over or under 6. If you guess correctly, you'll get a very special surprise!",
                "It's your lucky day!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        int newTotal = dice1 + dice2;

        String diceFile1 = Objects.requireNonNull(getClass().getResource("/images/Dice" + dice1 + ".png")).toExternalForm();
        String diceFile2 = Objects.requireNonNull(getClass().getResource("/images/Dice" + dice2 + ".png")).toExternalForm();
        Image diceImage1 = new Image(diceFile1);
        Image diceImage2 = new Image(diceFile2);
        dice1View.setImage(diceImage1);
        dice2View.setImage(diceImage2);


        String message;
        if (choice == 0 && newTotal > 6) {
            message = "Where my girls at?! You guessed correctly!";
            isLucky = true;
            player.deposit(event.getVendor(), event.getDescription(), event.getAmount());

        } else if (choice == 1 && newTotal < 6) {
            message = "Where my girls at?! You guessed correctly!";
            isLucky = true;
            player.deposit(event.getVendor(), event.getDescription(), event.getAmount());

        } else {
            isLucky = false;
           message = "You woke up and it was just a dream! No money for you this time.";
        }

        resultLabel.setText(message);
        balanceLabel.setText("Current Balance: $" + player.getBalance());

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

        String vendor = event.getVendor();
        double amount = event.getAmount();
        String message = event.getMessage();
        String fullMessage = message + "\nVendor: " + vendor + "\nAmount: " + String.format("$%.2f", amount);
        eventLabel.setText(fullMessage);



        if (diceTotal == 7 || diceTotal == 11) {
            eventLabel.setText("Lucky Roll! You rolled " + diceTotal + "!");

            javafx.application.Platform.runLater(() -> {
                        try {
                            Thread.sleep(350);
                        } catch (InterruptedException ignored) {
                        }

                resultLabel.setVisible(true);
                handleLuckyRoll(diceTotal);
                if (isLucky){
                eventLabel.setText(fullMessage);
                } else {
                    eventLabel.setText("You didn't win anything this time. Sorry pal!");
                }
            });
        } else {
            resultLabel.setVisible(false);
            if (event.getType() == LifeEvent.EventType.DEPOSIT) {
                player.deposit(event.getVendor(), event.getDescription(), event.getAmount());
            } else {
                player.spend(event.getVendor(), event.getDescription(), event.getAmount());
            }
        }
        balanceLabel.setText("Current Balance: $" + player.getBalance());

    }

    @FXML
    private void onResetGame(){
        player = new Player("Player1", 2100);
        balanceLabel.setText("Current Balance: $" + player.getBalance());
        eventLabel.setText("Welcome! \nYou just received your first Year Up stipend.\nSpend it wisely!");

        String startFile = Objects.requireNonNull(getClass().getResource("/images/Dice1.png")).toExternalForm();
        Image startImage = new Image(startFile);
        dice1View.setImage(startImage);
        dice2View.setImage(startImage);

        resultLabel.setVisible(false); }

    @FXML
    private void onViewLedger(){ player.viewLedger(); }

}
