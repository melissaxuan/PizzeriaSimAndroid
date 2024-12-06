package com.android.rupizzeria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rupizzeria.controllers.OrderRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import projects.rupizzeria.rupizzeria.util.Topping;

public class OrderActivity extends AppCompatActivity {
    private ArrayList<Topping> toppingList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Spinner spinner;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.order_view_real);
        spinner = findViewById(R.id.sp_pizzatype);

        // Create an ArrayAdapter using the string array from resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pizzatype, android.R.layout.simple_spinner_item);

        // Set the dropdown layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        recyclerView = findViewById(R.id.rv_orderRecyclerView);
        toppingList.addAll(Arrays.asList(Topping.values()));
        OrderRecyclerAdapter orderRecyclerAdapter = new OrderRecyclerAdapter(this, toppingList);
        recyclerView.setAdapter(orderRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        System.out.println("onCreate2");
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    public void onBackClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
//    private void setAdapter() {
//        OrderRecyclerAdapter adapter = new OrderRecyclerAdapter(this, toppingList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//    }
}