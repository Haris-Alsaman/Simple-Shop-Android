package com.example.shopapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InBasketProductsAsapter extends RecyclerView.Adapter<InBasketProductsAsapter.ViewHolder> {

    // List of InBasketProducts objects to be displayed in the RecyclerView
    List<InBasketProducts> productsList;
    // Context object
    Context context;

    // Constructor to set the productsList and context
    public InBasketProductsAsapter(List<InBasketProducts> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    // Set the productsList and notify the adapter of the data change
    public void setProductsList(List<InBasketProducts> productsList) {
        this.productsList = productsList;
        notifyDataSetChanged();
    }

    // Create a ViewHolder for the adapter
    @NonNull
    @Override
    public InBasketProductsAsapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each list item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbasketproductsshow, parent, false);
        // Return the ViewHolder
        return new InBasketProductsAsapter.ViewHolder(itemView);
    }

    // Bind data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Iterate over the products in the DataManager.productsList
        for (int i = 0; i < DataManager.productsList.size(); i++) {
            // If the ID of a product in the DataManager.productsList matches the ID of a product in the basket
            if (DataManager.productsList.get(i).getId().equals(productsList.get(position).getId())) {
                Products product = DataManager.productsList.get(i);
                // Set the product name, amount, and total price to the appropriate TextViews
                holder.basketname.setText(product.getName());
                holder.basketamount.setText("Amount: " + productsList.get(position).getAmount());
                double DouAmount = Double.valueOf(productsList.get(position).getAmount());
                double price = Double.valueOf(product.getPrice());
                double total = DouAmount * price;
                holder.baskettotal.setText("Price: " + total + " TL");
                // Add the total price of the product to the DataManager.BasketTotalPrice variable
                DataManager.BasketTotalPrice += total;

                // Load the product image into the ImageView using the Picasso library
                Picasso.get().load(product.getImage()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("data", "data");
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(product.getImage()).into(holder.imageView);
                    }
                });
            }
        }
    }

    // Return the number of items in the adapter
    @Override
    public int getItemCount() {
        return productsList.size();
    }

    // Define the ViewHolder for the adapter
    public class ViewHolder extends RecyclerView.ViewHolder {
        // TextViews and ImageView for the list item

        TextView basketname,baskettotal,basketamount;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            basketname = itemView.findViewById(R.id.basketName);
            baskettotal = itemView.findViewById(R.id.basketTotal);
            basketamount = itemView.findViewById(R.id.basketAmount);
            imageView = itemView.findViewById(R.id.basketImg);


        }
    }
}
