package com.example.musterikuyruguyonetimi;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginWithPhoneController {
    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginText;

    @FXML
    private TextField phoneTextField;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        if (phoneTextField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginText.setText("Please enter Phone Number!");
        }
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        try {
            loginText.setText("onCancelButtonClick");
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateLogin() {
        DatabaseController connectNow = new DatabaseController();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT * FROM customer WHERE idnumber = '" + phoneTextField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                String name = queryResult.getString("name");
                String surname = queryResult.getString("surname");
                int age = queryResult.getInt("age");
                int id = queryResult.getInt("idnumber");
                int phone = queryResult.getInt("phonenumber");

                Customer customer = new Customer(name, surname, age, id, phone);

                loginText.setText("Welcome " + name + " " + surname + "!");

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("actionPage.fxml"));
                        Parent root = loader.load();

                        ActionController actionController = loader.getController();
                        actionController.setCustomer(customer);

                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pause.play();
            } else {
                loginText.setText("Invalid Phone Number!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
