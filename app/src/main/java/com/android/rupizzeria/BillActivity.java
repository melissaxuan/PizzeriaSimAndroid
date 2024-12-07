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
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.rupizzeria.OrderActivity;
import com.android.rupizzeria.R;
import com.google.android.material.chip.Chip;
public class BillActivity extends AppCompatActivity {
    private Button backButton, exportButton, cancelButton;
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
        cancelButton = findViewById(R.id.cancelOrder);
        exportButton = findViewById(R.id.exportOrders);
        backButton = findViewById(R.id.backsButton);

    }
    public void onBackButtonBill(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
