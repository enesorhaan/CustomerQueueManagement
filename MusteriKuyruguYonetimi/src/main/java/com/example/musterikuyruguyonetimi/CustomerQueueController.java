package com.example.musterikuyruguyonetimi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

public class CustomerQueueController implements Initializable {
    @FXML
    private Button exitButton;
    @FXML
    private Button removeButton;
    @FXML
    private TableView<Customer> queueTable;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, Integer> numberColumn;

    private static Queue<Customer> customerQueue = new LinkedList<>();
    private static ObservableList<Customer> observableCustomerQueue = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        queueTable.setItems(observableCustomerQueue);
    }

    public void addCustomerToQueue(Customer customer) {
        customer.setNumber(customerQueue.size() + 1);
        customerQueue.offer(customer); // add Queue
        observableCustomerQueue.add(customer); // add ObservableList for view
    }

    @FXML
    protected void onExitButtonClick() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRemoveButtonClick() {
        Customer selectedCustomer = queueTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customerQueue.remove(selectedCustomer); // Remove Queue
            observableCustomerQueue.remove(selectedCustomer);

            updateCustomerNumbers();
        }
    }

    private void updateCustomerNumbers() {
        int index = 1;
        for (Customer customer : customerQueue) {
            customer.setNumber(index++);
        }
        observableCustomerQueue.setAll(customerQueue);
    }
}