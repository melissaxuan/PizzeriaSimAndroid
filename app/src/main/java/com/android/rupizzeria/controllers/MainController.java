package projects.rupizzeria.rupizzeria.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projects.rupizzeria.rupizzeria.util.Order;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main controller of the RUPizzeria program.
 *
 * @author Michael Ehresman
 */
public class MainController {
    private final int FIRST_ORDER_ID = 1;
    private final int ORDER_WIDTH = 614;
    private final int ORDER_HEIGHT = 547;
    private final int CURR_ORDER_WIDTH = 600;
    private final int CURR_ORDER_HEIGHT = 400;
    @FXML
    private Label welcomeText;

    @FXML
    private Button B_OrderPlaced;

    private Stage primaryStage; //the reference of the main window.
    private Scene primaryScene;  //the ref. of the scene set to the primaryStage
    private Order currentOrder;
    private ArrayList<Order> orderList;
    private int counter;

    /**
     * Default constructor for the MainController class.
     */
    public MainController()
    {
        this.counter = FIRST_ORDER_ID;
        this.currentOrder = new Order();
        this.orderList = new ArrayList<Order>();
    }

    /**
     * Sets the primary stage and scene for navigation.
     * @param stage to be set as the primary stage
     * @param scene to be set as the primary scene
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    /**
     * Navigates to the onOrdersPlaced scene bill-view.fxml when the image view is clicked.
     */
    @FXML
    public void onOrdersPlaced() {
        Stage popupStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projects/rupizzeria/rupizzeria/bill-view.fxml"));
            Scene popupScene = new Scene(loader.load(), 600, 600);
            popupStage.setScene(popupScene);
            popupStage.setTitle("Bill View");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();


            BillViewController secondViewController = loader.getController();

            secondViewController.setMainController(this, popupStage, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading bill-view.fxml.");
            alert.setContentText("Couldn't load bill-view.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Navigates to the oncurrentOrder scene current-order-view.fxml when the image vi ew is clicked.
     */
    @FXML
    public void onCurrentOrder() {
        Stage popupStage = new Stage(); // Create a new Stage for the popup

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projects/rupizzeria/rupizzeria/current-order-view.fxml"));
            Scene popupScene = new Scene(loader.load(), CURR_ORDER_WIDTH, CURR_ORDER_HEIGHT);
            popupStage.setScene(popupScene);
            popupStage.setTitle("Current Order");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();

            CurrentOrderController forthViewController = loader.getController();
            forthViewController.setMainController(this, popupStage, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading current-order-view.fxml.");
            alert.setContentText("Couldn't load current-order-view.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Navigates to the Order scene order-view.fxml when the image view is clicked.
     */
    @FXML
    public void Order() {
        Stage popupStage = new Stage();

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projects/rupizzeria/rupizzeria/order-view.fxml"));
            Scene popupScene = new Scene(loader.load(), ORDER_WIDTH, ORDER_HEIGHT);

            popupStage.setScene(popupScene);
            popupStage.setTitle("Order A Pizza");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.show();


            OrderController thirdViewController = loader.getController();
            thirdViewController.setMainController(this, popupStage, primaryStage, primaryScene);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading order-view.fxml.");
            alert.setContentText("Couldn't load order-view.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Getter method for the orderList arraylist.
     * @return the orderList
     */
    public ArrayList<Order> getOrder() {return orderList;}

    /**
     * Getter method for the current order.
     */
    public Order getCurrentOrder(){return this.currentOrder;}

    /**
     * Getter method for the counter.
     * @return the counter
     */
    public int getCounter(){return this.counter;}

    /**
     * Setter method for the current order.
     */
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }
    /**
     * Setter method for the counter.
     * @param index index to set counter to
     */
    public void setCounter(int index) {
        this.counter = index;
    }
    /**
     * Removes the order at the given index
     * @param index order to be removed
     */
    public void removeOrder(int index) {orderList.remove(index);}
}