package projects.rupizzeria.rupizzeria.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projects.rupizzeria.rupizzeria.pizza.Pizza;
import projects.rupizzeria.rupizzeria.util.Order;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Class to display the total bill of all the orders.
 * @author Michael Ehresman
 */
public class BillViewController {
    @FXML
    private ListView<String> lv_bill;
    @FXML
    private TextField tf_orderTotal;
    @FXML
    private Button B_cancelOrder;
    @FXML
    private Button B_exportStore;
    @FXML
    private ComboBox<String> cb_orderNumbers;

    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    /**
     * Helper method to calculate the total price of all the orders.
     * @return total price
     */
    private double totalPrice(int index)
    {
        double price = 0;
        ArrayList<Order> orders = mainController.getOrder();
        for (Order order : orders) {
            if(order.getNumber()==index)
            {
                price = order.calcOrderTotal();
            }
            }
        return price;

    }
    /**
     * Sets the main controller for navigation purposes.
     * @param controller   controller of the mainController
     * @param stage        stage of the mainController
     * @param primaryStage primaryStage of the mainController
     * @param primaryScene primaryScene of the mainController
     */
    public void setMainController(MainController controller, Stage stage, Stage primaryStage, Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
        tf_orderTotal.setText("0.00");
        printView();
    }

    /**
     * FXML method that highlights the correct order in the list view that was selected in the combo box.
     * @param actionEvent order to be highlighted
     */
    @FXML
    public void highLightOrder(ActionEvent actionEvent) {

        String selectedOrderNumber = cb_orderNumbers.getValue();
        if(selectedOrderNumber ==null)
        {
            return;
        }
        int orderNum = Integer.parseInt(selectedOrderNumber);
        ArrayList<Order> orders = mainController.getOrder();
        int counter =0;
        Order pickOrder = new Order();
        for (Order order : orders) {
            if(order.getNumber()==orderNum)
            {
                pickOrder = order;
                break;
            }
            counter ++;
        }
        lv_bill.getItems().clear();

        lv_bill.getItems().add(String.valueOf(pickOrder.toString()));
        DecimalFormat df = new DecimalFormat("0.00");
        tf_orderTotal.setText(String.valueOf(df.format(totalPrice(orderNum))));

    }

    /**
     * Cancels the order number selected in the combo box.
     * @param actionEvent order to be canceled
     */
    @FXML
    public void cancelOrder(ActionEvent actionEvent) {
        String selectedOrder = cb_orderNumbers.getValue();
        if (selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Please select an order to cancel");
            alert.showAndWait();
            return;
        }

        ArrayList<Order> orders = mainController.getOrder();
        for (Order order : orders) {
            if (order.getNumber()==Integer.parseInt(selectedOrder)) {
                selectedOrder = String.valueOf(order.getNumber());
                cb_orderNumbers.getItems().remove(selectedOrder);
                mainController.getOrder().remove(order);
                break;
            }
        }
        lv_bill.getItems().removeFirst();
        printView();
    }
        /**
         * Prints the orderlist to the list view,total price and adds the order numbers to the combo box.
         */
        public void printView () {

            ArrayList<Order> orders = mainController.getOrder();
            cb_orderNumbers.getItems().clear();
            for (Order order : orders) {
                cb_orderNumbers.getItems().add(String.valueOf(order.getNumber()));
            }
        }

    /**
     * Method to create a text file then export the orders into the text file.
     * @param actionEvent button click
     */
    @FXML
    public void onExportStore(ActionEvent actionEvent) {

        ArrayList<Order> orders = mainController.getOrder();

        if (orders.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Please add orders to export");
            alert.showAndWait();
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Orders");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                for (Order order: orders) {
                    writer.write(order.toString()+ System.lineSeparator());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Success! Orders were exported to " +file.getAbsolutePath());
                alert.showAndWait();
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failure!");
                alert.setHeaderText(null);
                alert.setContentText("Export failed please try again");
                alert.showAndWait();
            }
            }
        }
}


