//package com.example.rupizzeria.controllers;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import projects.rupizzeria.rupizzeria.util.Order;
//import java.util.ArrayList;
//
//import androidx.activity.EdgeToEdge;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.android.rupizzeria.OrderActivity;
//import com.android.rupizzeria.R;
//import com.google.android.material.chip.Chip;
//
///**
// * Main controller for the RUPizzeria app.
// */
//public class MainController extends AppCompatActivity {
//    private static final int FIRST_ORDER_ID = 1;
//    private Order currentOrder;
//    private ArrayList<Order> orderList;
//    private int counter;
//
//    private TextView welcomeText;
//    private ImageButton orderButton,currentOrderButton, billbutton;
//
//
//    /**
//     * Default constructor for the MainController class.
//     */
//    public MainController() {
//        this.counter = FIRST_ORDER_ID;
//        this.currentOrder = new Order();
//        this.orderList = new ArrayList<>();
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        findID();
//    }
//    private void findID()
//    {
//        orderButton = findViewById(R.id.OrderButton);
//        currentOrderButton = findViewById(R.id.CurrentOrderButton);
//        billbutton = findViewById(R.id.BillButton);
//
//    }
//
//
//    // Method called when "Order" button is clicked
//    public void onOrderClicked(View view) {
//        Intent intent = new Intent(this, OrderActivity.class);
//        startActivity(intent);
//    }
//
//    // Method called when "Current Order" button is clicked
//    //public void onCurrentOrderClicked(View view) {
//    //    Intent intent = new Intent(this, projects.rupizzeria.rupizzeria.controllers.CurrentOrderController.class);
//   //     startActivity(intent);
//   // }
//
//    // Method called when "Orders Placed" button is clicked
//  //  public void onOrdersPlacedClicked(View view) {
//    //    Intent intent = new Intent(this, projects.rupizzeria.rupizzeria.controllers.BillViewController.class);
//  //      startActivity(intent);
//  //  }
//
//    /**
//     * Getter method for the orderList.
//     * @return the orderList
//     */
//    public ArrayList<Order> getOrder() {
//        return orderList;
//    }
//
//    /**
//     * Getter method for the current order.
//     */
//    public Order getCurrentOrder() {
//        return this.currentOrder;
//    }
//
//    /**
//     * Getter method for the counter.
//     * @return the counter
//     */
//    public int getCounter() {
//        return this.counter;
//    }
//
//    /**
//     * Setter method for the current order.
//     */
//    public void setCurrentOrder(Order order) {
//        this.currentOrder = order;
//    }
//
//    /**
//     * Setter method for the counter.
//     * @param index index to set counter to
//     */
//    public void setCounter(int index) {
//        this.counter = index;
//    }
//
//    /**
//     * Removes the order at the given index
//     * @param index order to be removed
//     */
//    public void removeOrder(int index) {
//        orderList.remove(index);
//    }
//
//    /**
//     * Show an error alert dialog.
//     */
//    private void showErrorDialog(String errorMessage) {
//        new AlertDialog.Builder(this)
//                .setTitle("ERROR")
//                .setMessage(errorMessage)
//                .setPositiveButton("OK", null)
//                .show();
//    }
//}
