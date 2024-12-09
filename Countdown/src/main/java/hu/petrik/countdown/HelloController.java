package hu.petrik.countdown;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.w3c.dom.Text;
import javafx.event.ActionEvent;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;


import java.awt.*;

public class HelloController {

    @FXML
    private javafx.scene.text.Text displayCountdown;
    @FXML
    private Button CountdownButton;
    @FXML
    private TextField timeInput;

    public void initialize() {
        timeInput.setText("");
    }
    @FXML
    public void startCountdown(ActionEvent actionEvent) {
        LocalDateTime targetDateTime = LocalDateTime.parse(timeInput.getText(), DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss"));
        if (targetDateTime.isBefore(LocalDateTime.now())) {
            showAlert("Invalid Time", "Az időnek a jövőben kell lennie!");
            return;
        }
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                LocalDateTime nowDateTime = LocalDateTime.now();
                Duration duration = Duration.between(nowDateTime, targetDateTime);
                if (!duration.isNegative() && !duration.isZero()) {
                    long years = duration.toDays() / 365;
                    long months = (duration.toDays() % 365) / 30;
                    long days = (duration.toDays() % 365) % 30;
                    long hours = duration.toHours() % 24;
                    long minutes = duration.toMinutes() % 60;
                    long seconds = duration.getSeconds() % 60;
                    displayCountdown.setText(String.format("%d év %d hónap %d nap %02d:%02d:%02d", years, months, days, hours, minutes, seconds));
                } else {
                    displayCountdown.setText("Az idő lejárt!");
                    Platform.runLater(() -> showAlert("Countdown", "Az idő lejárt!"));
                    stop();
                }
            }
        };
        timer.start();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}