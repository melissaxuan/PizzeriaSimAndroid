
package com.android.rupizzeria.pizza.impl;


import com.android.rupizzeria.pizza.Pizza;
import com.android.rupizzeria.util.Crust;
import com.android.rupizzeria.util.Size;
import com.android.rupizzeria.util.Topping;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles orders for Meatzza.
 *
 * @author Melissa Xuan
 */
public class Meatzza extends Pizza {
    public static final Crust CHICAGO = Crust.STUFFED;
    public static final Crust NEW_YORK = Crust.HANDTOSSED;
    public static final double SMALL_PRICE = 17.99;
    public static final double MED_PRICE = 19.99;
    public static final double LARGE_PRICE = 21.99;
    public final double FREE = 0.0;

    /**
     * Default Meatzza constructor.
     */
    public Meatzza() {
        super();
        super.setToppings(new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM)));
        super.setCrust(Crust.STUFFED);
        super.setSize(Size.SMALL);
    }

    /**
     * Calculates price of Meatzza pizza.
     * @return price of Meatzza pizza.
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
