package com.android.rupizzeria.pizza;


import com.android.rupizzeria.pizza.impl.BBQChicken;
import com.android.rupizzeria.pizza.impl.BuildYourOwn;
import com.android.rupizzeria.pizza.impl.Deluxe;
import com.android.rupizzeria.pizza.impl.Meatzza;

/**
 * Handles orders for New York-style pizza.
 *
 * @author Melissa Xuan
 */
public class NYPizza implements PizzaFactory {
    /**
     * Creates New York-style Deluxe pizza.
     * @return New York-style Deluxe pizza
     */
    public Pizza createDeluxe() {
        return new Deluxe();
    }

    /**
     * Creates New York-style Meatzza pizza.
     * @return New York-style Meatzza
     */
    public Pizza createMeatzza() {
        return new Meatzza();
    }

    /**
     * Creates New York-style BBQ Chicken pizza.
     * @return New York-style BBQ Chicken pizza
     */
    public Pizza createBBQChicken() {
        return new BBQChicken();
    }

    /**
     * Creates New York-style Build Your Own pizza.
     * @return New York-style Build Your Own pizza
     */
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn();
    }
}
