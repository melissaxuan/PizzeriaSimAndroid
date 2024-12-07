package com.android.rupizzeria.controllers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rupizzeria.OrderActivity;
import com.android.rupizzeria.R;
import com.android.rupizzeria.util.Topping;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.MyViewHolder> {
    private final int MAX_TOPPINGS = 7;
    private Context context;
    private ArrayList<Topping> toppingList;
    private ArrayList<Integer> toppingImageList;

    private Set<Integer> selectedToppingList;

    public OrderRecyclerAdapter(Context context, ArrayList<Topping> toppingList, ArrayList<Integer> toppingImageList) {
        this.context = context;
        this.toppingList = toppingList;
        this.toppingImageList = toppingImageList;
        this.selectedToppingList = new HashSet<>();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String topping = toppingList.get(position).name();
        Integer toppingImage = toppingImageList.get(position);
        holder.ch_topping.setText(topping);

        holder.iv_toppingImage.setImageResource(toppingImageList.get(position));



        // Check if this position is selected and set chip state
        holder.ch_topping.setChecked(selectedToppingList.contains(position));

        // Set click listener to handle selection
        holder.ch_topping.setOnClickListener((Chip) -> {
            if (holder.ch_topping.isChecked()) {
                // Add the position to the selected set
                if (selectedToppingList.size() >= MAX_TOPPINGS) {
                    Toast.makeText(holder.itemView.getContext(), R.string.max_toppings, Toast.LENGTH_SHORT).show();
                    holder.ch_topping.setChecked(false);
                }
                else
                    selectedToppingList.add(position);

            } else {
                // Remove the position from the selected set
                selectedToppingList.remove(position);



            }
        });

        Log.i("TOPPINGS", selectedToppingList.toString() + " ");

    }

    @Override
    public int getItemCount() {
        Log.i("SIZE", toppingList.size() + " ");

        return toppingList.size();

    }

    public Set<Integer> getSelectedToppingList() {
        return selectedToppingList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private Chip ch_topping; // replace
        private ImageView iv_toppingImage;
        public MyViewHolder(final View view) {
            super(view);
            ch_topping = view.findViewById(R.id.ch_topping);
            iv_toppingImage = view.findViewById(R.id.iv_toppingimage);
        }
    }
}
