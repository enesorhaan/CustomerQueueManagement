package com.example.musterikuyruguyonetimi;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ActionController {

    @FXML
    private Button accountButton;
    @FXML
    private Button cardButton;
    @FXML
    private Button customerServiceButton;
    @FXML
    private Button otherServiceButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label actionWelcomeText;
    @FXML
    private Label actionText;

    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        actionWelcomeText.setText("Select Action " + customer.getName() + " " + customer.getSurname());
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        try {
            actionText.setText("onCancelButtonClick");
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAccountButtonClick() { processCustomerTransaction("Account Transaction", accountButton); }

    @FXML
    protected void onCardButtonClick() {
        processCustomerTransaction("Card Transaction", cardButton);
    }

    @FXML
    protected void onCustomerServiceButtonClick() { processCustomerTransaction("Customer Services", customerServiceButton); }

    @FXML
    protected void onOtherServiceButtonClick() {
        processCustomerTransaction("Other Services", otherServiceButton);
    }

    private void processCustomerTransaction(String transaction, Button pressedButton) {
        actionText.setText(customer.getName() + " " + customer.getSurname() + " added to queue!");
        customer.setTransaction(transaction);

        CustomerQueueController customerQueueController = new CustomerQueueController();
        customerQueueController.addCustomerToQueue(customer);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Stage stage = (Stage) pressedButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}
