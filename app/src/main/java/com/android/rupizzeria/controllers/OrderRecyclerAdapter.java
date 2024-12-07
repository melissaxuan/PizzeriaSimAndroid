package com.android.rupizzeria.controllers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rupizzeria.R;
import com.android.rupizzeria.util.Topping;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;


public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Topping> toppingList;
    private ArrayList<Drawable> toppingImageList;

    public OrderRecyclerAdapter(Context context, ArrayList<Topping> toppingList) {
        this.context = context;
        this.toppingList = toppingList;
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
        Drawable toppingImage = toppingImageList.get(position);
        holder.ch_topping.setText(topping);
        holder.ch_topping.setChipIcon(toppingImage);
        Log.i("TOPPING", topping + " added at position " + position);
    }

    @Override
    public int getItemCount() {
        Log.i("SIZE", toppingList.size() + " ");

        return toppingList.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private Chip ch_topping; // replace

        public MyViewHolder(final View view) {
            super(view);
            ch_topping = view.findViewById(R.id.ch_topping);
        }
    }
}
