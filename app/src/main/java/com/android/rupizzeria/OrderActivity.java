package com.android.rupizzeria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rupizzeria.controllers.OrderRecyclerAdapter;
import com.android.rupizzeria.pizza.ChicagoPizza;
import com.android.rupizzeria.pizza.Pizza;
import com.android.rupizzeria.pizza.PizzaFactory;
import com.android.rupizzeria.util.Topping;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<Topping> toppingList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Spinner spinner;
    private Button back;

    private RadioButton chicagoCrust, nyCrust, smallSize, medSize, largeSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_view_real);
        spinner = findViewById(R.id.sp_pizzatype);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pizzatype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        recyclerView = findViewById(R.id.rv_orderRecyclerView);
        toppingList.addAll(Arrays.asList(Topping.values()));
        OrderRecyclerAdapter orderRecyclerAdapter = new OrderRecyclerAdapter(this, toppingList);
        recyclerView.setAdapter(orderRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String p = spinner.getSelectedItem().toString();
        PizzaFactory pizzaFactory = new ChicagoPizza();
        Pizza pizza;

        switch (p) {
//            case (getResources().getStringArray(R.array.pizzatype)[0]) : {pizza = pizzaFactory.
        }


//        chicagoCrust.setText()

        Toast.makeText(this, p, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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