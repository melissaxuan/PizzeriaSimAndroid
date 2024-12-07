package com.android.rupizzeria.pizza.impl;



import com.android.rupizzeria.pizza.Pizza;
import com.android.rupizzeria.util.Crust;
import com.android.rupizzeria.util.Size;
import com.android.rupizzeria.util.Topping;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles orders for BBQ Chicken Pizza.
 */
public class BBQChicken extends Pizza {
    public static final double SMALL_PRICE = 14.99;
    public static final double MED_PRICE = 16.99;
    public static final double LARGE_PRICE = 19.99;
    public final double FREE = 0.0;
    /**
     * Default BBQChicken constructor.
     */
    public BBQChicken() {
        super();
        super.setToppings(new ArrayList<>(Arrays.asList(Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE, Topping.CHEDDAR)));
        super.setCrust(Crust.PAN);
        super.setSize(Size.SMALL);
    }

    /**
     * Calculates price of BBQChicken pizza.
     * @return price of BBQChicken pizza
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
