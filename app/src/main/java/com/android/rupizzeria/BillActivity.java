package com.android.rupizzeria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.rupizzeria.OrderActivity;
import com.android.rupizzeria.R;
import com.android.rupizzeria.util.Order;
import com.android.rupizzeria.util.SingletonData;
import com.google.android.material.chip.Chip;
public class BillActivity extends AppCompatActivity {

    private ListView lv_bill;
    private TextView tf_orderTotal;
    private Button B_cancelOrder, B_exportStore,backButton;
    private Spinner sp_orderNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bill_view);

        setContentView(R.layout.bill_view);
        findID();
    }
    private void findID()
    {
        B_cancelOrder = findViewById(R.id.cancelOrder);
        B_exportStore = findViewById(R.id.exportOrders);
        backButton = findViewById(R.id.backsButton);
        lv_bill=findViewById(R.id.ordersList);
        tf_orderTotal=findViewById(R.id.priceView);
        sp_orderNumbers=findViewById(R.id.orderNumbers);

    }
    /**
     * Helper method to calculate the total price of all the orders.
     * @return total price
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
     * FXML method that highlights the correct order in the list view that was selected in the combo box.
     * @param actionEvent order to be highlighted
     */
    /**
     * Highlights the correct order in the ListView based on the selected item in the Spinner.
     */
    public void highLightOrder(View view) {
        String selectedOrderNumber = sp_orderNumbers.getSelectedItem().toString();
        if (selectedOrderNumber == null) {
            return;
        }
        int orderNum = Integer.parseInt(selectedOrderNumber);
        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();

        Order pickOrder = null;
        for (Order order : orders) {
            if (order.getNumber() == orderNum) {
                pickOrder = order;
                break;
            }
        }

        if (pickOrder != null) {
            // Wrap pickOrder.toString() into a List
            List<String> orderDetails = Collections.singletonList(pickOrder.toString());

            // Use ArrayAdapter with the List
            lv_bill.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderDetails));

            // Format and display the total price
            DecimalFormat df = new DecimalFormat("0.00");
            tf_orderTotal.setText(df.format(totalPrice(orderNum)));
        }

    }

    /**
     * Cancels the order number selected in the Spinner.
     */
    public void cancelOrder(View view) {
        String selectedOrder = (String) sp_orderNumbers.getSelectedItem();
        if (selectedOrder == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Please select an order to cancel")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();
        for (Order order : orders) {
            if (order.getNumber() == Integer.parseInt(selectedOrder)) {
                orders.remove(order);
                break;
            }
        }

        printView(); // Refresh the view
    }
    /**
     * Populates the Spinner and ListView with the current orders.
     */
    public void printView() {
        ArrayList<Order> orders = SingletonData.getInstance().getOrderList();

        // Populate the Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        for (Order order : orders) {
            spinnerAdapter.add(String.valueOf(order.getNumber()));
        }
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_orderNumbers.setAdapter(spinnerAdapter);

        // Clear the ListView and EditText
        lv_bill.setAdapter(null);
        tf_orderTotal.setText("");
    }

    public void onBackButtonBill(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
