package com.example.shopapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class theProduct extends Fragment {

int intAmount = 1;
String id ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_product, container, false);
        Context context = view.getContext();

        // Get the current product
        Products product = DataManager.productsList.get(DataManager.productNumber);
        id = product.getId();

        // Get the UI elements
        TextView name = view.findViewById(R.id.FragmentproductName);
        ImageView imageView = view.findViewById(R.id.FragmentproductImg);
        TextView des = view.findViewById(R.id.FragmentproductDes);
        TextView Price = view.findViewById(R.id.FragmentproductPrice);
        TextView Amount = view.findViewById(R.id.FragmentproductAmount);
        Button btn_add = view.findViewById(R.id.FragmentAdd);
        ImageView plus = view.findViewById(R.id.FragmentPlus);
        ImageView minus = view.findViewById(R.id.FragmentMinus);

        // Set the UI elements
        Amount.setText(Integer.toString(intAmount));
        Price.setText(product.getPrice()+" TL");
        des.setText(product.getDescription());
        name.setText(product.getName());
        Picasso.get().load(product.getImage()).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            // Load the image using Picasso library

            @Override
            public void onSuccess() {
                Log.d("veri","veri");
            }
            @Override
            public void onError(Exception e) {

                Picasso.get().load(product.getImage()).into(imageView); // If the image is not cached, fetch it from the server
            }
        });

        // Add a click listener to the plus button
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount.setText(Integer.toString(++intAmount));
                double DouAmount = Double.valueOf(intAmount);
                double price = Double.valueOf(product.getPrice());
                double ddon= DouAmount * price;
                Price.setText(ddon + " Tl");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intAmount > 0) {
                    Amount.setText(Integer.toString(--intAmount));
                    double DouAmount = Double.valueOf(intAmount);
                    double price = Double.valueOf(product.getPrice());
                    double ddon= DouAmount * price;
                    Price.setText(ddon + " Tl");

                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0 ; i < DataManager.BasketList.size(); i++) {
                    if (DataManager.BasketList.get(i).getId().equals(id)) {

                        if (DataManager.BasketList.get(i).getAmount() ==intAmount )
                            Toast.makeText(context, "You already have",Toast.LENGTH_SHORT).show();
                        else {
                            DataManager.BasketList.get(i).setAmount(intAmount);
                            Toast.makeText(context, "Basket has been updated", Toast.LENGTH_SHORT).show();

                        }
                        return;
                    }
                }
                if (intAmount > 0) {
                    DataManager.BasketList.add(new InBasketProducts(id, intAmount));
                    Toast.makeText(context,"Added to basket",Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }
}