package com.example.musterikuyruguyonetimi;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;


public class MainController {
    @FXML
    private Button newCustomerButton;

    @FXML
    private Button phoneButton;

    @FXML
    private Button idButton;

    @FXML
    private Button queueButton;

    @FXML
    protected void onNewCustomerButtonClick() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage stage = (Stage) newCustomerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onPhoneButtonClick() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("loginWithPhone.fxml"));
            Stage stage = (Stage) phoneButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onIdButtonClick() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("loginWithId.fxml"));
            Stage stage = (Stage) idButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onQueueButtonClick() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("customerQueue.fxml"));
            Stage stage = (Stage) queueButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}