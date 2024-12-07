package com.android.rupizzeria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.rupizzeria.util.Order;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.rupizzeria.OrderActivity;
import com.android.rupizzeria.R;
import com.google.android.material.chip.Chip;

/**
 * Class to display the main screen activity.
 * @author Michael Ehresman
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton orderButton,currentOrderButton, billbutton;
    @Override
    /**
     * Overidden method to run the current activity.
     */
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
    /**
     * Helper method to find the ID's for all the variables.
     */
    private void findID()
    {
        orderButton = findViewById(R.id.OrderButton);
        currentOrderButton = findViewById(R.id.CurrentOrderButton);
        billbutton = findViewById(R.id.BillButton);

    }


    /**
     * Method to launch the order activity on the order button press.
     * @param view current view
     */
    public void onOrderClicked(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
    /**
     * Method to launch the bill activity on the bill button press.
     * @param view current view
     */
    public void onBillClicked(View view) {
        Intent intent = new Intent(this, BillActivity.class);
        startActivity(intent);
    }
    /**
     * Method to launch the currentOrder activity on the currentOrder button press.
     * @param view current view
     */
    public void onCurrentClicked(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

}