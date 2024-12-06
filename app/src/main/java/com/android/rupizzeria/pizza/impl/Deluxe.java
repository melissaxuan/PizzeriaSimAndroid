package projects.rupizzeria.rupizzeria.pizza.impl;

import projects.rupizzeria.rupizzeria.pizza.Pizza;
import projects.rupizzeria.rupizzeria.util.Crust;
import projects.rupizzeria.rupizzeria.util.Size;
import projects.rupizzeria.rupizzeria.util.Topping;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles orders for Deluxe Pizza.
 */
public class Deluxe extends Pizza {
    public static final double SMALL_PRICE = 16.99;
    public static final double MED_PRICE = 18.99;
    public static final double LARGE_PRICE = 20.99;
    public final double FREE = 0.0;
    /**
     * Default Deluxe constructor.
     */
    public Deluxe() {
        super();
        super.setToppings(new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION,Topping.MUSHROOM)));
        super.setCrust(Crust.DEEPDISH);
        super.setSize(Size.SMALL);
    }

    /**
     * Calculates price of Deluxe pizza.
     * @return price of Deluxe pizza
     */
    public double price() {
        switch(super.getSize()) {
            case SMALL : {return SMALL_PRICE;}
            case MEDIUM : {return MED_PRICE;}
            case LARGE : {return LARGE_PRICE;}
            default : {return FREE;}
        }
    }
}


