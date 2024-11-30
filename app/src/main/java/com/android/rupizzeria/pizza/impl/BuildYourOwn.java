package projects.rupizzeria.rupizzeria.pizza.impl;

import projects.rupizzeria.rupizzeria.pizza.Pizza;
import projects.rupizzeria.rupizzeria.util.Crust;
import projects.rupizzeria.rupizzeria.util.Size;
import projects.rupizzeria.rupizzeria.util.Topping;

import java.util.ArrayList;

/**
 * Handles orders for Build Your Own Pizza.
 *
 * @author Melissa Xuan
 */
public class BuildYourOwn extends Pizza {
    public static final double SMALL_PRICE = 8.99;
    public static final double MED_PRICE = 10.99;
    public static final double LARGE_PRICE = 12.99;
    public static final double TOPPING_PRICE = 1.69;
    private final double FREE = 0.0;
    private final double HUNDRED = 100.0;
    private final int MAX_TOPPINGS = 7;

    /**
     * Default constructor for BuildYourOwn pizza.
     */
    public BuildYourOwn() {
        super();
        super.setToppings(new ArrayList<>());
        super.setCrust(Crust.PAN);
        super.setSize(Size.SMALL);
    }

    /**
     * Add a topping to BuildYourOwn pizza if there are less than 7 toppings on the pizza.
     * @param topping topping to be added to the pizza
     */
    public void addTopping(Topping topping) {
        if (super.getToppings().size() < MAX_TOPPINGS)
            super.getToppings().add(topping);
    }

    /**
     * Calculates the price of the BuildYourOwn Pizza.
     * @return price of the BuildYourOwn Pizza
     */
    public double price() {
        double toppingsPrice = super.getToppings().size() * TOPPING_PRICE;
        switch(super.getSize()) {
            case SMALL -> {return Math.round((SMALL_PRICE + toppingsPrice) * HUNDRED) / HUNDRED;}
            case MEDIUM -> {return Math.round((MED_PRICE + toppingsPrice) * HUNDRED) / HUNDRED;}
            case LARGE -> {return Math.round((LARGE_PRICE + toppingsPrice) * HUNDRED) / HUNDRED;}
            default -> {return FREE;}
        }
    }
}
