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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import projects.rupizzeria.rupizzeria.util.Order;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.rupizzeria.OrderActivity;
import com.android.rupizzeria.R;
import com.google.android.material.chip.Chip;


public class MainActivity extends AppCompatActivity {
    private static final int FIRST_ORDER_ID = 1;
    private Order currentOrder;
    private ArrayList<Order> orderList;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setContentView(R.layout.activity_main);
        findID();
    }
    private void findID()
    {
        orderButton = findViewById(R.id.OrderButton);
        currentOrderButton = findViewById(R.id.CurrentOrderButton);
        billbutton = findViewById(R.id.BillButton);

    }

    private ImageButton orderButton,currentOrderButton, billbutton;
    // Method called when "Order" button is clicked
    public void onOrderClicked(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
    public void onBillClicked(View view) {
        Intent intent = new Intent(this, BillActivity.class);
        startActivity(intent);
    }

}