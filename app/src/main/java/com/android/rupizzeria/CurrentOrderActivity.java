package com.android.rupizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.rupizzeria.pizza.Pizza;
import com.android.rupizzeria.util.Order;
import com.android.rupizzeria.util.SingletonData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to display the current order screen activity.
 * @author Melissa Xuan
 */
public class CurrentOrderActivity extends AppCompatActivity {
    private static final int COUNT_INCR = 1;
    private ListView pizzas;

    private TextView orderno, subtotal, salesTax, orderTotal;
    @Override
    /**
     * Overidden method to run the current activity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.current_order_view);


        refreshPage();
    }
    private Button backedButton;

    /**
     * Helper method to find the ID's for all the variables.
     */
    private void findID()
    {
        backedButton = findViewById(R.id.backButtonn);
    }
    /**
     * Method to load the main activity after the back button was pressed.
     * @param view current view
     */
    public void onBackCurrent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Removes a pizza from the current order.
     * @param view current view
     */
    public void onRemovePizza(View view) {
        final Pizza[] selectedItem = new Pizza[1];
        final int[] p = new int[1];
        // Set an item click listener to handle item selection
        pizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                selectedItem[0] = (Pizza) parent.getItemAtPosition(position);
                p[0] = position;
                // Show a Toast with the selected item
                SingletonData.getInstance().getCurrentOrder().removePizza(p[0]);

                String toast = getString(R.string.remove_pizza) + selectedItem[0].toString();
                Toast.makeText(pizzas.getContext(), toast, Toast.LENGTH_SHORT).show();

                refreshPage();
            }
        });
    }

    /**
     * Places the current order and clear the view.
     * @param view the current view
     */
    public void onPlaceOrder(View view) {
        if (!SingletonData.getInstance().getCurrentOrder().getPizzas().isEmpty()) {
            Order o = new Order(SingletonData.getInstance().getCurrentOrder());
            SingletonData.getInstance().getOrderList().add(o);

            String toast = getString(R.string.order_placed) + o.getNumber();
            Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();

            SingletonData.getInstance().setCounter(SingletonData.getInstance().getCounter() + COUNT_INCR);

            refreshPage();
        }
    }

    /**
     * Clears the current order.
     * @param view the current view
     */
    public void onClearOrder(View view) {

        SingletonData.getInstance().getOrderList().remove(SingletonData.getInstance().getCurrentOrder());
        SingletonData.getInstance().setCounter(SingletonData.getInstance().getCounter() + COUNT_INCR);
        SingletonData.getInstance().setCurrentOrder(new Order(SingletonData.getInstance().getCounter()));

        refreshPage();


    }

    /**
     * refreshes the current order page.
     */
    private void refreshPage() {
        pizzas = findViewById(R.id.lv_orderList);


        orderno = findViewById(R.id.txt_orderno);
        subtotal = findViewById(R.id.txt_subtotal);
        salesTax = findViewById(R.id.txt_salesTax);
        orderTotal = findViewById(R.id.txt_orderTotal);

        Order order = SingletonData.getInstance().getCurrentOrder();

        if (order != null) {
            // Use ArrayAdapter with the List
            pizzas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order.getPizzas()));

            String orderNumber = getString(R.string.order_no) + order.getNumber();
            orderno.setText(orderNumber);

            // Format and display the total price
            DecimalFormat df = new DecimalFormat("0.00");
            String subtotalAmount = getString(R.string.subtotal) + df.format(order.calcSubtotal());
            String salesTaxAmount = getString(R.string.tax) + df.format(order.calcTaxes());
            String orderTotalAmount = getString(R.string.order_total) + df.format(order.calcOrderTotal());
            subtotal.setText(subtotalAmount);
            salesTax.setText(salesTaxAmount);
            orderTotal.setText(orderTotalAmount);
        }
    }
}
