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

/**
 * Handles adapter for order recycler.
 *
 * @author Melissa Xuan
 */
public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.MyViewHolder> {
    private final int MAX_TOPPINGS = 7;
    private Context context;
    private ArrayList<Topping> toppingList;
    private ArrayList<Integer> toppingImageList;

    private Set<Integer> selectedToppingList;

    /**
     * Constructor.
     * @param context context for adapter
     * @param toppingList list of toppings
     * @param toppingImageList list of topping images
     */
    public OrderRecyclerAdapter(Context context, ArrayList<Topping> toppingList, ArrayList<Integer> toppingImageList) {
        this.context = context;
        this.toppingList = toppingList;
        this.toppingImageList = toppingImageList;
        this.selectedToppingList = new HashSet<>();
    }


    /**
     * Handles creating view holder.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return view holder
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_view, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Handles when binding view holder.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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
    }

    /**
     * Returns item counts.
     * @return item count
     */
    @Override
    public int getItemCount() {
        Log.i("SIZE", toppingList.size() + " ");

        return toppingList.size();

    }

    /**
     * Returns topping list.
     * @return set of topping list.
     */
    public Set<Integer> getSelectedToppingList() {
        return selectedToppingList;
    }

    /**
     * Resets selected topping list.
     */
    public void resetSelectedToppingList() {
        selectedToppingList = new HashSet<>();
    }

    /**
     * Helper class for recycler view.
     */
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
