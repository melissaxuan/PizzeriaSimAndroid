package com.android.rupizzeria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.android.rupizzeria.pizza.impl.BBQChicken;
import com.android.rupizzeria.pizza.impl.BuildYourOwn;
import com.android.rupizzeria.pizza.impl.Deluxe;
import com.android.rupizzeria.pizza.impl.Meatzza;
import com.android.rupizzeria.util.Topping;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to display the order activity
 * @author Melissa Xuan
 */
public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final int IDX_ZERO = 0;
    private final int IDX_ONE = 1;
    private final int IDX_TWO = 2;
    private final int IDX_THREE = 3;

    private ArrayList<Topping> toppingList = new ArrayList<>();
    private ArrayList<Drawable> toppingImageList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Spinner spinner;
    private Button back;

    private RadioButton chicagoCrust, nyCrust, smallSize, medSize, largeSize;

    @Override
    /**
     * Overidden method to run the current activity
     */
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
//        toppingImageList.add(R.drawable.)
        OrderRecyclerAdapter orderRecyclerAdapter = new OrderRecyclerAdapter(this, toppingList);
        recyclerView.setAdapter(orderRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String p = spinner.getSelectedItem().toString();
        PizzaFactory pizzaFactory = new ChicagoPizza();
        Pizza pizza;
        String[] s = getResources().getStringArray(R.array.pizzatype);

        chicagoCrust = findViewById(R.id.rb_chicago);
        nyCrust = findViewById(R.id.rb_ny);

        smallSize = findViewById(R.id.rb_small);
        medSize = findViewById(R.id.rb_medium);
        largeSize = findViewById(R.id.rb_large);

        if (p.equals(s[IDX_ZERO])) {
            pizza = pizzaFactory.createDeluxe();
            setupDeluxe();
        }
        else if (p.equals(s[IDX_ONE])) {
            pizza = pizzaFactory.createBBQChicken();
            setupBBQChicken();
        }
        else if (p.equals(s[IDX_TWO])) {
            pizza = pizzaFactory.createMeatzza();
            setupMeatzza();
        }
        else if (p.equals(s[IDX_THREE])) {
            pizza = pizzaFactory.createBuildYourOwn();
            setupBYO();
        }





//        chicagoCrust.setText()

        Toast.makeText(this, p, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    /**
     * Method to load the main activity after the back button was pressed
     * @param view current view
     */
    public void onBackClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setupDeluxe() {
        chicagoCrust.setText(R.string.deluxe_chicago);
        nyCrust.setText(R.string.deluxe_ny);
        String s = getString(R.string.small_price) + " " + Deluxe.SMALL_PRICE;
        String m = getString(R.string.medium_price) + " " + Deluxe.MED_PRICE;
        String l = getString(R.string.large_price) + " " + Deluxe.LARGE_PRICE;
        smallSize.setText(s);
        medSize.setText(m);
        largeSize.setText(l);
    }

    private void setupBBQChicken() {
        chicagoCrust.setText(R.string.bbq_chicago);
        nyCrust.setText(R.string.bbq_ny);
        String s = getString(R.string.small_price) + " " + BBQChicken.SMALL_PRICE;
        String m = getString(R.string.medium_price) + " " + BBQChicken.MED_PRICE;
        String l = getString(R.string.large_price) + " " + BBQChicken.LARGE_PRICE;
        smallSize.setText(s);
        medSize.setText(m);
        largeSize.setText(l);
    }

    private void setupMeatzza() {
        chicagoCrust.setText(R.string.meatzza_chicago);
        nyCrust.setText(R.string.meatzza_ny);
        String s = getString(R.string.small_price) + " " + Meatzza.SMALL_PRICE;
        String m = getString(R.string.medium_price) + " " + Meatzza.MED_PRICE;
        String l = getString(R.string.large_price) + " " + Meatzza.LARGE_PRICE;
        smallSize.setText(s);
        medSize.setText(m);
        largeSize.setText(l);
    }

    private void setupBYO() {
        chicagoCrust.setText(R.string.byo_chicago);
        nyCrust.setText(R.string.byo_ny);
        String s = getString(R.string.small_price) + " " + BuildYourOwn.SMALL_PRICE;
        String m = getString(R.string.medium_price) + " " + BuildYourOwn.MED_PRICE;
        String l = getString(R.string.large_price) + " " + BuildYourOwn.LARGE_PRICE;
        smallSize.setText(s);
        medSize.setText(m);
        largeSize.setText(l);
    }
}