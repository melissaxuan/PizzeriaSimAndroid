package com.android.rupizzeria.util;

import projects.rupizzeria.rupizzeria.pizza.Pizza;

import java.util.ArrayList;

/**
 * Keeps track of Order details.
 */
public class Order {
    private final int FIRST_ORDER_ID = 1;
    private final double NJ_SALES_TAX_RATE = 0.06625;
    private final double INITIAL_TOTAL = 0.0;
    private final double HUNDRED = 100.0;
    private int number;
    private ArrayList<Pizza> pizzas;

    /**
     * Default constructor for Order.
     */
    public Order() {
        this.number = FIRST_ORDER_ID;
        this.pizzas = new ArrayList<Pizza>();
    }

    /**
     * Copy constructor for Order object.
     * @param o Order object to copy values from
     */
    public Order(Order o) {
        this.number = o.number;
        this.pizzas = o.pizzas;
    }

    /**
     * Parameter constructor to create a new Order with a set Order number.
     * @param id the number for this Order
     */
    public Order(int id) {
        this.number = id;
        this.pizzas = new ArrayList<Pizza>();
    }

    /**
     * Add the Pizza to the Order.
     * @param pizza Pizza to be added to the Order
     */
    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    /**
     * Removes the Pizza at the specified index of the Pizza list in Order.
     * @param index index of the Pizza to be removed from Pizza list in Order
     */
    public void removePizza(int index) {
        this.pizzas.remove(index);
    }

    /**
     * Calculates subtotal of Order.
     * @return subtotal of Order
     */
    public double calcSubtotal() {
        double subtotal = INITIAL_TOTAL;

        for (Pizza p : this.pizzas) {
            subtotal += p.price();
        }

        return Math.round(subtotal * HUNDRED) / HUNDRED;
    }

    /**
     * Calculates sales tax of Order.
     * @return sales tax of Order
     */
    public double calcTaxes() {
        return Math.round((calcSubtotal() * NJ_SALES_TAX_RATE) * HUNDRED) / HUNDRED;
    }

    /**
     * Calculates order total of Order.
     * @return total cost of Order
     */
    public double calcOrderTotal() {
        return Math.round((calcSubtotal() + calcTaxes()) * HUNDRED) / HUNDRED;
    }

    /**
     * Returns Order ID number.
     * @return Order number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns list of Pizzas in this Order.
     * @return list of Pizzas in this Order
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Sets Order ID number.
     * @param number Order ID number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return pizzas.toString();
    }
}
