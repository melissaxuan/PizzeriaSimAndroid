package projects.rupizzeria.rupizzeria.controllers;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projects.rupizzeria.rupizzeria.pizza.Pizza;
import projects.rupizzeria.rupizzeria.util.Order;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Controls current order view.
 *
 * @author Melissa Xuan
 */
public class CurrentOrderController {
    private final int COUNT_INCR = 1;
    private final int ORDER_WIDTH = 614;
    private final int ORDER_HEIGHT = 547;
    @FXML
    private Button bt_addpizza;

    @FXML
    private Button bt_placeorder;

    @FXML
    private Button bt_removeorder;

    @FXML
    private Button bt_removepizza;

    @FXML
    private ListView<Pizza> lv_pizzas;

    @FXML
    private Text txt_currentorderheader;

    @FXML
    private Text txt_orderno;

    @FXML
    private Text txt_ordertotal;

    @FXML
    private Text txt_subtotal;

    @FXML
    private Text txt_tax;

    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    /**
     * sets the main controller for navigation purposes
     * @param controller controller of the mainController
     * @param stage stage of the mainController
     * @param primaryStage primaryStage of the mainController
     * @param primaryScene primaryScene of the mainController
     */
    public void setMainController(MainController controller, Stage stage, Stage primaryStage, Scene primaryScene) {
        this.mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
        refreshPage();
    }

    /**
     * Redirects user to order view to add a new pizza to order.
     * @param event action event
     */
    @FXML
    void addPizza(ActionEvent event) {
        Stage popupStage = new Stage(); // Create a new Stage for the popup

        try {
            // Load the order-view.fxml for the popup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projects/rupizzeria/rupizzeria/order-view.fxml"));
            Scene popupScene = new Scene(loader.load(), ORDER_WIDTH, ORDER_HEIGHT);

            // Set the scene for the popup stage
            popupStage.setScene(popupScene);
            popupStage.setTitle("Order A Pizza");
            popupStage.initModality(Modality.APPLICATION_MODAL); // Make it modal (blocks interaction with other windows)
            popupStage.show(); // Show the popup window

            // Pass references to the OrderController
            OrderController thirdViewController = loader.getController();
            thirdViewController.setMainController(this.mainController, popupStage, this.primaryStage, this.primaryScene);

            stage.close();
        } catch (IOException e) {
            // Handle exceptions with an alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading order-view.fxml.");
            alert.setContentText("Couldn't load order-view.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Removes Pizza from list of Pizzas in Order.
     * @param event action event
     */
    @FXML
    void removePizza(ActionEvent event) {
        if (this.lv_pizzas.getSelectionModel().getSelectedItem() != null) {
            this.mainController.getCurrentOrder().removePizza(this.lv_pizzas.getSelectionModel().getSelectedIndex());
            refreshPage();
        }
    }

    /**
     * Places Order and increments Counter.
     * @param event action event
     */
    @FXML
    void placeOrder(ActionEvent event) {
        if (!this.mainController.getCurrentOrder().getPizzas().isEmpty()) {
            Order o = new Order(this.mainController.getCurrentOrder());
            this.mainController.getOrder().add(o);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText(null);
            alert.setContentText("Order " + this.mainController.getCurrentOrder().getNumber() + " placed.");
            alert.showAndWait();

            this.mainController.setCounter(this.mainController.getCounter() + COUNT_INCR);
            this.mainController.setCurrentOrder(new Order(this.mainController.getCounter()));
            refreshPage();
        }
    }

    /**
     * Clears pizzas from current order.
     * @param event action event
     */
    @FXML
    void clearOrder(ActionEvent event) {
        if (!this.mainController.getCurrentOrder().getPizzas().isEmpty()) {
            this.mainController.setCurrentOrder(new Order(this.mainController.getCounter()));
            refreshPage();
        }
    }

    /**
     * Helper method to refresh page with updated prices and updated pizzas in current order.
     */
    private void refreshPage() {
        lv_pizzas.setItems(FXCollections.observableArrayList(this.mainController.getCurrentOrder().getPizzas()));
        txt_orderno.setText("Order number: " + this.mainController.getCurrentOrder().getNumber());

        DecimalFormat df = new DecimalFormat("0.00");
        String subtotal = df.format(this.mainController.getCurrentOrder().calcSubtotal());
        String tax = df.format(this.mainController.getCurrentOrder().calcTaxes());
        String total = df.format(this.mainController.getCurrentOrder().calcOrderTotal());
        txt_subtotal.setText("Subtotal: $" + subtotal);
        txt_tax.setText("Sales Tax: $" + tax);
        txt_ordertotal.setText("Order Total: $" + total);
    }
}
