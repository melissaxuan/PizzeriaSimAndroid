package projects.rupizzeria.rupizzeria.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projects.rupizzeria.rupizzeria.pizza.impl.Deluxe;
import projects.rupizzeria.rupizzeria.pizza.impl.BBQChicken;
import projects.rupizzeria.rupizzeria.pizza.impl.Meatzza;
import projects.rupizzeria.rupizzeria.pizza.impl.BuildYourOwn;
import projects.rupizzeria.rupizzeria.pizza.ChicagoPizza;
import projects.rupizzeria.rupizzeria.pizza.NYPizza;
import projects.rupizzeria.rupizzeria.pizza.Pizza;
import projects.rupizzeria.rupizzeria.pizza.PizzaFactory;
import projects.rupizzeria.rupizzeria.util.Crust;
import projects.rupizzeria.rupizzeria.util.Size;
import projects.rupizzeria.rupizzeria.util.Topping;

import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controls order view.
 *
 * @author Melissa Xuan
 */
public class OrderController {
    private final double MIN_OPACITY = 0.0;
    private final double MAX_OPACITY = 1.0;
    private final int MAX_TOPPINGS = 7;
    private final double HUNDRED = 100.0;
    private final int ALERT_WIDTH = 480;
    private final int ALERT_HEIGHT = 150;
    private final int PIZZA_BASE = 0;
    private final int CHEDDAR = 1;
    private final int PEPPERONI = 2;
    private final int OXTAIL = 3;
    private final int ONION = 4;
    private final int PROVOLONE = 5;
    private final int GREEN_PEPPER = 6;
    private final int SAUSAGE = 7;
    private final int BEEF = 8;
    private final int HAM = 9;
    private final int SPINACH = 10;
    private final int BLACK_OLIVE = 11;
    private final int MUSHROOM = 12;
    private final int PINEAPPLE = 13;
    private final int BBQ_CHICKEN = 14;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_placeorder;

    @FXML
    private Button bt_remove;

    @FXML
    private ComboBox<String> cb_pizzatype;

    @FXML
    private StackPane sp_pizza;

    @FXML
    private ListView<Topping> lv_availtoppings;

    @FXML
    private ListView<Topping> lv_chosentoppings;

    @FXML
    private RadioButton rb_chicagocrust;

    @FXML
    private RadioButton rb_largesize;

    @FXML
    private RadioButton rb_mediumsize;

    @FXML
    private RadioButton rb_nycrust;

    @FXML
    private RadioButton rb_smallsize;

    @FXML
    private ToggleGroup tg_crust;

    @FXML
    private ToggleGroup tg_size;

    @FXML
    private Text txt_add;

    @FXML
    private Text txt_availabletoppings;

    @FXML
    private Text txt_chosentoppings;

    @FXML
    private Text txt_crust;

    @FXML
    private Text txt_price;

    @FXML
    private Text txt_priceheader;

    @FXML
    private Text txt_remove;

    @FXML
    private Text txt_size;

    @FXML
    private Text txt_toppings;

    private MainController mainController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;


    /**
     * Initializes order view.
     */
    @FXML
    void initialize() {
        cb_pizzatype.getItems().addAll(
                "Deluxe Pizza",
                "BBQ Chicken Pizza",
                "Meatzza Pizza",
                "Build Your Own Pizza"
        );
        lv_availtoppings.setItems(FXCollections.observableArrayList(Topping.values()));
        disableAll();
    }

    /**
     * sets the main controller for navigation purposes.
     * @param controller controller of the mainController
     * @param stage stage of the mainController
     * @param primaryStage primaryStage of the mainController
     * @param primaryScene primaryScene of the mainController
     */
    public void setMainController(MainController controller, Stage stage, Stage primaryStage, Scene primaryScene) {
        mainController = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    /**
     * Processes add button when adding toppings to Build Your Own Pizza.
     * @param event action event
     */
    @FXML
    void addTopping(ActionEvent event) {
        if (lv_availtoppings.getSelectionModel().getSelectedItem() != null &&
            lv_chosentoppings.getItems().size() < MAX_TOPPINGS) {
            selectionToppingPic(lv_availtoppings.getSelectionModel().getSelectedItem(), MAX_OPACITY);
            lv_chosentoppings.getItems().add(lv_availtoppings.getSelectionModel().getSelectedItem());
            lv_availtoppings.getItems().remove(lv_availtoppings.getSelectionModel().getSelectedIndex());

        }
        else if (lv_availtoppings.getSelectionModel().getSelectedItem() != null &&
            lv_chosentoppings.getItems().size() >= MAX_TOPPINGS) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Maximum Toppings");
            alert.setHeaderText(null);
            alert.setContentText("There can only be a maximum of seven (7) toppings on a Build Your Own Pizza.");
            alert.showAndWait();
        }

        setupBYOPrice();
    }

    /**
     * Processes add pizza to order button and adds pizza to current order.
     * @param event action event
     */
    @FXML
    void placePizzaOrder(ActionEvent event) {
        String crust = "Chicago";
        PizzaFactory pizzaFactory;
        if (tg_crust.getSelectedToggle().toString().contains("Chicago Style")) {
            pizzaFactory = new ChicagoPizza();

        }
        else {
            crust = "NY";
            pizzaFactory = new NYPizza();
        }

        Pizza pizza = pizzaFactory.createDeluxe();
        pizza = setSpecialtyCrust(pizza, pizzaFactory, crust);

        if (tg_size.getSelectedToggle().toString().contains("Small")) {
            pizza.setSize(Size.SMALL);
        }
        else if (tg_size.getSelectedToggle().toString().contains("Medium")) {
            pizza.setSize(Size.MEDIUM);
        }
        else {
            pizza.setSize(Size.LARGE);
        }

        if (cb_pizzatype.getValue().equalsIgnoreCase("Build Your Own Pizza")) {
            pizza.setToppings(new ArrayList<>(lv_chosentoppings.getItems()));
        }
        mainController.getCurrentOrder().addPizza(pizza);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Added");
        alert.setHeaderText(null);
        alert.setContentText("Pizza was added to order " + mainController.getCurrentOrder().getNumber() + " with details: \n" + pizza.toString());
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(ALERT_WIDTH, ALERT_HEIGHT);
        alert.showAndWait();

    }

    /**
     * Process remove topping button and removes topping from pizza.
     * @param event action event
     */
    @FXML
    void removeTopping(ActionEvent event) {
        if (lv_chosentoppings.getSelectionModel().getSelectedItem() != null) {
            selectionToppingPic(lv_chosentoppings.getSelectionModel().getSelectedItem(), MIN_OPACITY);
            lv_availtoppings.getItems().add(lv_chosentoppings.getSelectionModel().getSelectedItem());
            lv_chosentoppings.getItems().remove(lv_chosentoppings.getSelectionModel().getSelectedIndex());
        }
        setupBYOPrice();
    }

    /**
     * Processes selected pizza and makes rest of the page appear if this was the first pizza.
     * @param event action event
     */
    @FXML
    void selectPizzaType(ActionEvent event) {
        rb_chicagocrust.setSelected(true);
        rb_nycrust.setSelected(false);
        rb_smallsize.setSelected(true);
        rb_mediumsize.setSelected(false);
        rb_largesize.setSelected(false);
        enablePlaceOrder();
        switch(cb_pizzatype.getValue()) {
            case "Deluxe Pizza" -> {
                setupDeluxe();
                break;
            }
            case "BBQ Chicken Pizza" -> {
                setupBBQChicken();
                break;
            }
            case "Meatzza Pizza" -> {
                setupMeatzza();
                break;
            }
            case "Build Your Own Pizza" -> {
                setupBYO();
                break;
            }
        }
    }

    /**
     * Updates view with specific price when small pizza size is chosen.
     * @param event action event
     */
    @FXML
    void selectSmall(ActionEvent event) {
        switch(cb_pizzatype.getValue()) {
            case "Deluxe Pizza" -> {
                setupDeluxePrice();
                break;
            }
            case "BBQ Chicken Pizza" -> {
                setupBBQChickenPrice();
                break;
            }
            case "Meatzza Pizza" -> {
                setupMeatzzaPrice();
                break;
            }
            case "Build Your Own Pizza" -> {
                setupBYOPrice();
                break;
            }
        }
    }

    /**
     * Updates view with specific price when medium pizza size is chosen.
     * @param event action event
     */
    @FXML
    void selectMedium(ActionEvent event) {
        switch(cb_pizzatype.getValue()) {
            case "Deluxe Pizza" -> {
                setupDeluxePrice();
                break;
            }
            case "BBQ Chicken Pizza" -> {
                setupBBQChickenPrice();
                break;
            }
            case "Meatzza Pizza" -> {
                setupMeatzzaPrice();
                break;
            }
            case "Build Your Own Pizza" -> {
                setupBYOPrice();
                break;
            }
        }
    }

    /**
     * Updates view with specific price when large pizza size is chosen.
     * @param event action event
     */
    @FXML
    void selectLarge(ActionEvent event) {
        switch(cb_pizzatype.getValue()) {
            case "Deluxe Pizza" -> {
                setupDeluxePrice();
                break;
            }
            case "BBQ Chicken Pizza" -> {
                setupBBQChickenPrice();
                break;
            }
            case "Meatzza Pizza" -> {
                setupMeatzzaPrice();
                break;
            }
            case "Build Your Own Pizza" -> {
                setupBYOPrice();
                break;
            }
        }
    }

    /**
     * Helper method to update pizza price display for deluxe pizza.
     */
    private void setupDeluxePrice() {
        if (tg_size.getSelectedToggle().toString().contains("Small")) {
            txt_price.setText("$" + Deluxe.SMALL_PRICE);
        }
        else if (tg_size.getSelectedToggle().toString().contains("Medium")) {
            txt_price.setText("$" + Deluxe.MED_PRICE);
        }
        else {
            txt_price.setText("$" + Deluxe.LARGE_PRICE);
        }
    }

    /**
     * Helper method to update pizza price display for BBQ Chicken pizza.
     */
    private void setupBBQChickenPrice() {
        if (tg_size.getSelectedToggle().toString().contains("Small")) {
            txt_price.setText("$" + BBQChicken.SMALL_PRICE);
        }
        else if (tg_size.getSelectedToggle().toString().contains("Medium")) {
            txt_price.setText("$" + BBQChicken.MED_PRICE);
        }
        else {
            txt_price.setText("$" + BBQChicken.LARGE_PRICE);
        }
    }

    /**
     * Helper method to update pizza price display for Meatzza pizza.
     */
    private void setupMeatzzaPrice() {
        if (tg_size.getSelectedToggle().toString().contains("Small")) {
            txt_price.setText("$" + Meatzza.SMALL_PRICE);
        }
        else if (tg_size.getSelectedToggle().toString().contains("Medium")) {
            txt_price.setText("$" + Meatzza.MED_PRICE);
        }
        else {
            txt_price.setText("$" + Meatzza.LARGE_PRICE);
        }
    }

    /**
     * Helper method to update pizza price display for Build Your Own pizza.
     */
    private void setupBYOPrice() {
        double toppingsPrice = lv_chosentoppings.getItems().size() * BuildYourOwn.TOPPING_PRICE;
        if (tg_size.getSelectedToggle().toString().contains("Small")) {
            txt_price.setText("$" + Math.round((BuildYourOwn.SMALL_PRICE + toppingsPrice) * HUNDRED) / HUNDRED);
        }
        else if (tg_size.getSelectedToggle().toString().contains("Medium")) {
            txt_price.setText("$" + Math.round((BuildYourOwn.MED_PRICE + toppingsPrice) * HUNDRED) / HUNDRED);
        }
        else {
            txt_price.setText("$" + Math.round((BuildYourOwn.LARGE_PRICE + toppingsPrice) * HUNDRED) / HUNDRED);
        }
    }

    /**
     * Enables the crust and size options for Deluxe pizza, and its toppings list.
     */
    private void setupDeluxe() {
        enableCrust();
        rb_chicagocrust.setText("Chicago Style: Deep Dish");
        rb_nycrust.setText("NY Style: Brooklyn");
        enableSize();
        rb_smallsize.setText("Small: $" + Deluxe.SMALL_PRICE);
        rb_mediumsize.setText("Medium: $" + Deluxe.MED_PRICE);
        rb_largesize.setText("Large: $" + Deluxe.LARGE_PRICE);
        showFixedToppings();
        lv_availtoppings.setItems(FXCollections.observableArrayList(new ArrayList<Topping>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM))));
        for (Topping t : lv_availtoppings.getItems()) {
            selectionToppingPic(t, MAX_OPACITY);
        }
        txt_price.setText("$" + Deluxe.SMALL_PRICE);
    }

    /**
     * Enables the crust and size options for BBQ Chicken pizza, and its toppings list.
     */
    private void setupBBQChicken() {
        enableCrust();
        rb_chicagocrust.setText("Chicago Style: Pan");
        rb_nycrust.setText("NY Style: Thin");
        enableSize();
        rb_smallsize.setText("Small: $" + BBQChicken.SMALL_PRICE);
        rb_mediumsize.setText("Medium: $" + BBQChicken.MED_PRICE);
        rb_largesize.setText("Large: $" + BBQChicken.LARGE_PRICE);
        showFixedToppings();
        lv_availtoppings.getItems().removeAll();
        lv_availtoppings.setItems(FXCollections.observableArrayList(new ArrayList<Topping>(Arrays.asList(
                Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE, Topping.CHEDDAR))));
        for (Topping t : lv_availtoppings.getItems()) {
            selectionToppingPic(t, MAX_OPACITY);
        }
        txt_price.setText("$" + BBQChicken.SMALL_PRICE);
    }

    /**
     * Enables the crust and size options for Meatzza pizza, and its toppings list.
     */
    private void setupMeatzza() {
        enableCrust();
        rb_chicagocrust.setText("Chicago Style: Stuffed");
        rb_nycrust.setText("NY Style: Hand-tossed");
        enableSize();
        rb_smallsize.setText("Small: $" + Meatzza.SMALL_PRICE);
        rb_mediumsize.setText("Medium: $" + Meatzza.MED_PRICE);
        rb_largesize.setText("Large: $" + Meatzza.LARGE_PRICE);
        showFixedToppings();
        lv_availtoppings.getItems().removeAll();
        lv_availtoppings.setItems(FXCollections.observableArrayList(new ArrayList<Topping>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM))));
        for (Topping t : lv_availtoppings.getItems()) {
            selectionToppingPic(t, MAX_OPACITY);
        }
        txt_price.setText("$" + Meatzza.SMALL_PRICE);
    }

    /**
     * Enables the crust, size, and toppings options for Build Your Own Pizza.
     */
    private void setupBYO() {
        enableAll();
        rb_chicagocrust.setText("Chicago Style: Pan");
        rb_nycrust.setText("NY Style: Hand-tossed");
        rb_smallsize.setText("Small: $" + BuildYourOwn.SMALL_PRICE);
        rb_mediumsize.setText("Medium: $" + BuildYourOwn.MED_PRICE);
        rb_largesize.setText("Large: $" + BuildYourOwn.LARGE_PRICE);
        txt_price.setText("$" + BuildYourOwn.SMALL_PRICE);
    }

    /**
     * Helper method to disable every feature on the page other than the type of pizza combo box select.
     */
    private void disableAll() {
        txt_crust.setOpacity(MIN_OPACITY);
        rb_chicagocrust.setOpacity(MIN_OPACITY);
        rb_nycrust.setOpacity(MIN_OPACITY);
        rb_chicagocrust.setDisable(true);
        rb_nycrust.setDisable(true);
        txt_size.setOpacity(MIN_OPACITY);
        rb_smallsize.setOpacity(MIN_OPACITY);
        rb_mediumsize.setOpacity(MIN_OPACITY);
        rb_largesize.setOpacity(MIN_OPACITY);
        rb_smallsize.setDisable(true);
        rb_mediumsize.setDisable(true);
        rb_largesize.setDisable(true);
        txt_toppings.setOpacity(MIN_OPACITY);
        txt_availabletoppings.setOpacity(MIN_OPACITY);
        txt_chosentoppings.setOpacity(MIN_OPACITY);
        lv_availtoppings.setOpacity(MIN_OPACITY);
        lv_chosentoppings.setOpacity(MIN_OPACITY);
        lv_availtoppings.setDisable(true);
        lv_chosentoppings.setDisable(true);
        txt_add.setOpacity(MIN_OPACITY);
        txt_remove.setOpacity(MIN_OPACITY);
        bt_add.setOpacity(MIN_OPACITY);
        bt_remove.setOpacity(MIN_OPACITY);
        bt_add.setDisable(true);
        bt_remove.setDisable(true);
        bt_placeorder.setOpacity(MIN_OPACITY);
        bt_placeorder.setDisable(true);
        txt_priceheader.setOpacity(MIN_OPACITY);
        txt_price.setOpacity(MIN_OPACITY);
        sp_pizza.setOpacity(MIN_OPACITY);
        for (Node child : sp_pizza.getChildren()) {
            child.setOpacity(MIN_OPACITY);
        }
    }

    /**
     * Helper method to enable every feature on the page other than the type of pizza combo box select.
     */
    private void enableAll() {
        enableCrust();
        enableSize();
        enableToppings();
        enablePlaceOrder();
    }

    /**
     * Helper method to enable crust options.
     */
    private void enableCrust() {
        txt_crust.setOpacity(MAX_OPACITY);
        rb_chicagocrust.setOpacity(MAX_OPACITY);
        rb_nycrust.setOpacity(MAX_OPACITY);
        rb_chicagocrust.setDisable(false);
        rb_nycrust.setDisable(false);
    }

    /**
     * Helper method to enable size options.
     */
    private void enableSize() {
        txt_size.setOpacity(MAX_OPACITY);
        rb_smallsize.setOpacity(MAX_OPACITY);
        rb_mediumsize.setOpacity(MAX_OPACITY);
        rb_largesize.setOpacity(MAX_OPACITY);
        rb_smallsize.setDisable(false);
        rb_mediumsize.setDisable(false);
        rb_largesize.setDisable(false);
    }

    /**
     * Helper method to enable toppings options and set the available toppings.
     */
    private void enableToppings() {
        txt_toppings.setOpacity(MAX_OPACITY);
        txt_availabletoppings.setOpacity(MAX_OPACITY);
        txt_chosentoppings.setOpacity(MAX_OPACITY);
        txt_availabletoppings.setText("Available Toppings (+$1.69 each)");
        lv_availtoppings.setOpacity(MAX_OPACITY);
        lv_chosentoppings.setOpacity(MAX_OPACITY);
        lv_availtoppings.setDisable(false);
        lv_chosentoppings.setDisable(false);
        txt_add.setOpacity(MAX_OPACITY);
        txt_remove.setOpacity(MAX_OPACITY);
        bt_add.setOpacity(MAX_OPACITY);
        bt_remove.setOpacity(MAX_OPACITY);
        bt_add.setDisable(false);
        bt_remove.setDisable(false);
        sp_pizza.setOpacity(MAX_OPACITY);

        for (Node child : sp_pizza.getChildren()) {
            child.setOpacity(MIN_OPACITY);
        }

        sp_pizza.getChildren().get(PIZZA_BASE).setOpacity(MAX_OPACITY);

        lv_availtoppings.getItems().removeAll();
        lv_availtoppings.setItems(FXCollections.observableArrayList(Topping.values()));

        lv_chosentoppings.getItems().removeAll();
    }

    /**
     * Helper method to enable place order buttons.
     */
    private void enablePlaceOrder() {
        bt_placeorder.setOpacity(MAX_OPACITY);
        bt_placeorder.setDisable(false);
        txt_priceheader.setOpacity(MAX_OPACITY);
        txt_price.setOpacity(MAX_OPACITY);

        sp_pizza.setOpacity(MAX_OPACITY);
        for (Node child : sp_pizza.getChildren()) {
            child.setOpacity(MIN_OPACITY);
        }

        sp_pizza.getChildren().get(PIZZA_BASE).setOpacity(MAX_OPACITY);
    }

    /**
     * Helper method to show fixed toppings for specialty pizzas.
     */
    private void showFixedToppings() {
        txt_toppings.setOpacity(MAX_OPACITY);
        txt_availabletoppings.setOpacity(MAX_OPACITY);
        txt_availabletoppings.setText("Fixed Toppings");
        txt_chosentoppings.setOpacity(MIN_OPACITY);
        lv_availtoppings.setOpacity(MAX_OPACITY);
        lv_chosentoppings.setOpacity(MIN_OPACITY);
        lv_availtoppings.setDisable(true);
        lv_chosentoppings.setDisable(true);
        txt_add.setOpacity(MIN_OPACITY);
        txt_remove.setOpacity(MIN_OPACITY);
        bt_add.setOpacity(MIN_OPACITY);
        bt_remove.setOpacity(MIN_OPACITY);
        bt_add.setDisable(true);
        bt_remove.setDisable(true);
    }

    /**
     * Helper method to set pizza crust and specialty type.
     */
    private Pizza setSpecialtyCrust(Pizza pizza, PizzaFactory pizzaFactory, String crust) {
        switch (cb_pizzatype.getValue()) {
            case "Deluxe Pizza" :
                pizza = pizzaFactory.createDeluxe();
                if (crust.equals("Chicago"))
                    pizza.setCrust(Crust.DEEPDISH);
                else
                    pizza.setCrust(Crust.BROOKLYN);
                return pizza;
            case "BBQ Chicken Pizza" :
                pizza = pizzaFactory.createBBQChicken();
                if (crust.equals("Chicago"))
                    pizza.setCrust(Crust.PAN);
                else
                    pizza.setCrust(Crust.THIN);
                return pizza;
            case "Meatzza Pizza" :
                pizza = pizzaFactory.createMeatzza();
                if (crust.equals("Chicago"))
                    pizza.setCrust(Crust.STUFFED);
                else
                    pizza.setCrust(Crust.HANDTOSSED);
                return pizza;
            case "Build Your Own Pizza" :
                pizza = pizzaFactory.createBuildYourOwn();
                if (crust.equals("Chicago"))
                    pizza.setCrust(Crust.PAN);
                else
                    pizza.setCrust(Crust.HANDTOSSED);
                return pizza;
        }
        return pizza;
    }

    /**
     * Helper method to handle switching the toppings on the pizza image.
     * @param topping topping to change on the pizza image
     * @param opacity opacity to set the image to
     */
    private void selectionToppingPic(Topping topping, double opacity) {
        switch (topping.name()) {
            case "CHEDDAR" -> {sp_pizza.getChildren().get(CHEDDAR).setOpacity(opacity);}
            case "PEPPERONI" -> {sp_pizza.getChildren().get(PEPPERONI).setOpacity(opacity);}
            case "OXTAIL" -> {sp_pizza.getChildren().get(OXTAIL).setOpacity(opacity);}
            case "ONION" -> {sp_pizza.getChildren().get(ONION).setOpacity(opacity);}
            case "PROVOLONE" -> {sp_pizza.getChildren().get(PROVOLONE).setOpacity(opacity);}
            case "GREEN_PEPPER" -> {sp_pizza.getChildren().get(GREEN_PEPPER).setOpacity(opacity);}
            case "SAUSAGE" -> {sp_pizza.getChildren().get(SAUSAGE).setOpacity(opacity);}
            case "BEEF" -> {sp_pizza.getChildren().get(BEEF).setOpacity(opacity);}
            case "HAM" -> {sp_pizza.getChildren().get(HAM).setOpacity(opacity);}
            case "SPINACH" -> {sp_pizza.getChildren().get(SPINACH).setOpacity(opacity);}
            case "BLACK_OLIVES" -> {sp_pizza.getChildren().get(BLACK_OLIVE).setOpacity(opacity);}
            case "MUSHROOM" -> {sp_pizza.getChildren().get(MUSHROOM).setOpacity(opacity);}
            case "PINEAPPLE" -> {sp_pizza.getChildren().get(PINEAPPLE).setOpacity(opacity);}
            case "BBQ_CHICKEN" -> {sp_pizza.getChildren().get(BBQ_CHICKEN).setOpacity(opacity);}
        }
    }
}
