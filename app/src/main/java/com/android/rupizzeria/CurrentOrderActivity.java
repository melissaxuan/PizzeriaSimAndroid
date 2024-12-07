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

public class CurrentOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.current_order_view);

    }
    private Button backedButton;
    private void findID()
    {
        backedButton = findViewById(R.id.backButtonn);
    }
    public void onBackCurrent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
