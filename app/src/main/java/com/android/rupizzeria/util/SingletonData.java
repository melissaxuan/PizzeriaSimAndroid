package com.android.rupizzeria.util;

import java.util.ArrayList;

/**
 * Singleton class to keep track of list of Orders, current Order, and counter for Order ID.
 *
 * @author Melissa Xuan
 */
public final class SingletonData {
    private final int FIRST_ORDER_ID = 1;

    private static SingletonData singletonData;

    private ArrayList<Order> orderList = new ArrayList<Order>();
    private Order currentOrder = new Order();
    private int counter = FIRST_ORDER_ID;

    /**
     * Default constructor that is set to private so new SingletonData object can't be called.
     */
    private SingletonData() {
    }

    /**
     * Returns this instance of SingletonData.
     * @return this instance of SingletonData
     */
    public static synchronized SingletonData getInstance() {
        if (singletonData == null) {
            singletonData = new SingletonData();
        }
        return singletonData;
    }

    /**
     * Getter method for list of Orders.
     * @return orderList
     */
    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    /**
     * Setter method for list of Orders.
     * @param orderList ArrayList of Orders to be set for orderList
     */
    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    /**
     * Getter method for current Order.
     * @return current Order object
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Setter method for current Order.
     * @param currentOrder Order object to be set for current Order
     */
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Getter method for Order counter.
     * @return counter that keeps track of Order IDs
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Setter method for Order counter.
     * @param counter counter to be set in place of current counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
