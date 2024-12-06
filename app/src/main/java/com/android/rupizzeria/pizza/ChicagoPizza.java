package com.android.rupizzeria.pizza;

import projects.rupizzeria.rupizzeria.pizza.impl.BBQChicken;
import projects.rupizzeria.rupizzeria.pizza.impl.BuildYourOwn;
import projects.rupizzeria.rupizzeria.pizza.impl.Deluxe;
import projects.rupizzeria.rupizzeria.pizza.impl.Meatzza;

/**
 * Class for the chicago pizza choice.
 * @author Michael Ehresman
 */
public class ChicagoPizza implements PizzaFactory {
    /**
     * Method to createDeluxe pizzas.
     * @return new deluxe pizza
     */
    public Pizza createDeluxe() {
        return new Deluxe();
    }
    /**
     * Method to create Meatzza pizzas.
     * @return new Meatzza pizza
     */
    public Pizza createMeatzza() {
        return new Meatzza();
    }
    /**
     * Method to create BBQChicken pizzas.
     * @return new BBQChicken pizza
     */
    public Pizza createBBQChicken() {
        return new BBQChicken();
    }
    /**
     * Method to create your own pizzas.
     * @return new self created pizza
     */
    public Pizza createBuildYourOwn() {return new BuildYourOwn();}
}
