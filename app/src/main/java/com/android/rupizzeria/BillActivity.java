package com.android.rupizzeria;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.android.rupizzeria.util.Order;
import com.android.rupizzeria.util.SingletonData;

/**
 * Class to display the Bill screen activity.
 * @author Michael Ehresman
 */
public class BillActivity extends AppCompatActivity {

    private ListView lv_bill;
    private TextView tf_orderTotal;
    private Button B_cancelOrder, B_exportStore, backButton;
    private Spinner sp_orderNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bill_view);

        findID();
        setupSpinnerListener(); // Attach the listener to the Spinner
        printView();
    }

    /**
     * Helper method to find the ID's for all the variables.
     */
    private void findID() {
        B_cancelOrder = findViewById(R.id.cancelOrder);
        B_exportStore = findViewById(R.id.exportOrders);
        backButton = findViewById(R.id.backsButton);
        lv_bill = findViewById(R.id.ordersList);
        tf_orderTotal = findViewById(R.id.priceView);
        sp_orderNumbers = findViewById(R.id.orderNumbers);
    }

    /**
     * Attaches an OnItemSelectedListener to the Spinner.
     */
    private void setupSpinnerListener() {
        sp_orderNumbers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onSpinSelect(parent, view, position, id); // Call the onSpinSelect method
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle the case when nothing is selected
            }
        });
    }

    /**
     * Calculates the total price of an order based on its index.
     * @param index The order index.
     * @return Total price of the selected order.
     */
    private double totalPrice(int index) {
        double price = 0;
        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();
        for (Order order : orders) {
            if (order.getNumber() == index) {
                price = order.calcOrderTotal();
            }
        }
        return price;
    }

    /**
     * Handles spinner item selection to display order details and total price.
     */
    public void onSpinSelect(AdapterView<?> parent, View view, int position, long id) {
        String selectedOrderNumber = sp_orderNumbers.getSelectedItem().toString();
        if (selectedOrderNumber == null || selectedOrderNumber.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("No Selection")
                    .setMessage("Please select an order to display.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        int orderNum = Integer.parseInt(selectedOrderNumber);
        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();

        Order selectedOrder = null;
        for (Order order : orders) {
            if (order.getNumber() == orderNum) {
                selectedOrder = order;
                break;
            }
        }

        // Update the ListView and total price if the order exists
        if (selectedOrder != null) {
            List<String> orderDetails = Collections.singletonList(selectedOrder.toString());
            lv_bill.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderDetails));

            DecimalFormat df = new DecimalFormat("0.00");
            tf_orderTotal.setText(df.format(totalPrice(orderNum)));
        }
    }

    /**
     * Cancels the order number selected in the Spinner.
     */
    public void cancelOrder(View view) {
        String selectedOrder = (String) sp_orderNumbers.getSelectedItem();
        if (selectedOrder == null || selectedOrder.isEmpty()) {
            Toast.makeText(this, "Please select an order to cancel", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();
        for (Order order : orders) {
            if (order.getNumber() == Integer.parseInt(selectedOrder)) {
                orders.remove(order);
                break;
            }
        }

        printView(); // Refresh the view after canceling the order
    }

    /**
     * Populates the Spinner and ListView with the current orders.
     */
    public void printView() {
        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        for (Order order : orders) {
            spinnerAdapter.add(String.valueOf(order.getNumber()));
        }
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_orderNumbers.setAdapter(spinnerAdapter);

        lv_bill.setAdapter(null);
        tf_orderTotal.setText("");
    }

    /**
     * Method to create a text file and export the orders into the text file.
     * @param view Button click
     */
    public void onExportStore(View view) {
        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();

        if (orders.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("No Orders")
                    .setMessage("Please add orders to export.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "orders.txt");
        startActivityForResult(intent, 1);
    }

    /**
     * Handles the result of the file picker Intent.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                try (FileOutputStream fos = (FileOutputStream) getContentResolver().openOutputStream(uri);
                     OutputStreamWriter writer = new OutputStreamWriter(fos)) {

                    ArrayList<Order> orders = SingletonData.getInstance().getOrderList();
                    for (Order order : orders) {
                        writer.write(order.toString() + System.lineSeparator());
                    }

                    Toast.makeText(this, "Orders exported successfully!", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Export failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * Method to load the main activity after the back button is pressed.
     * @param view Current view
     */
    public void onBackButtonBill(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
