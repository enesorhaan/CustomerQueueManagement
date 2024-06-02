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
import java.sql.*;

public class RegisterController {
    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label registerText;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    protected void onRegisterButtonClick() {
        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() ||
                ageTextField.getText().isEmpty() || idTextField.getText().isEmpty() ||
                phoneTextField.getText().isEmpty()) {
            registerText.setText("Fill in all fields!");
        } else {
            DatabaseController connectNow = new DatabaseController();
            Connection connectDB = connectNow.getConnection();

            String searchCustomerWithID = "SELECT count(1) FROM customer WHERE idnumber = ?";
            String searchCustomerWithPhone = "SELECT count(1) FROM customer WHERE phonenumber = ?";

            try {
                // ResultSet for ID Number
                PreparedStatement preparedStatementForID = connectDB.prepareStatement(searchCustomerWithID);
                preparedStatementForID.setString(1, idTextField.getText());
                ResultSet queryResultForID = preparedStatementForID.executeQuery();

                boolean customerWithIDExists = false;
                if (queryResultForID.next() && queryResultForID.getInt(1) > 0) {
                    customerWithIDExists = true;
                }
                queryResultForID.close();
                preparedStatementForID.close();

                // ResultSet for Phone Number
                PreparedStatement preparedStatementForPhone = connectDB.prepareStatement(searchCustomerWithPhone);
                preparedStatementForPhone.setString(1, phoneTextField.getText());
                ResultSet queryResultForPhone = preparedStatementForPhone.executeQuery();

                boolean customerWithPhoneExists = false;
                if (queryResultForPhone.next() && queryResultForPhone.getInt(1) > 0) {
                    customerWithPhoneExists = true;
                }
                queryResultForPhone.close();
                preparedStatementForPhone.close();

                // Is customer registered check
                if (customerWithIDExists || customerWithPhoneExists) {
                    registerText.setText("Customer is already registered!");
                } else {
                    registerUser();

                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(event -> {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                            Stage stage = (Stage) registerButton.getScene().getWindow();
                            stage.setScene(new Scene(root));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    pause.play();
                }

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        try {
            registerText.setText("onCancelButtonClick");
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerUser() {
        DatabaseController connectNow = new DatabaseController();
        Connection connectDB = connectNow.getConnection();

        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String age = ageTextField.getText();
        String id = idTextField.getText();
        String phone = phoneTextField.getText();

        String insertToRegister = "INSERT INTO customer(name, surname, age, idnumber, phonenumber) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(insertToRegister);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, id);
            preparedStatement.setString(5, phone);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            registerText.setText("Registration Successful!");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
