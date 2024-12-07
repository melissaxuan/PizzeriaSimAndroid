package com.android.rupizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/**
 * Class to display the current order screen activity
 * @author Melissa Xuan
 */
public class CurrentOrderActivity extends AppCompatActivity {
    @Override
    /**
     * Overidden method to run the current activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.current_order_view);

    }
    private Button backedButton;

    /**
     * Helper method to find the ID's for all the variables
     */
    private void findID()
    {
        backedButton = findViewById(R.id.backButtonn);
    }
    /**
     * Method to load the main activity after the back button was pressed
     * @param view current view
     */
    public void onBackCurrent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
