package com.example.shopapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class main extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_main, container, false);

        // Find the "go shopping" button and set a click listener
        Button goShopping = view.findViewById(R.id.btnGoShop);
        goShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the shopping fragment
                Navigation.findNavController(view).navigate(R.id.action_main_to_shop);
            }
        });

        // Find the "go to basket" button and set a click listener
        Button goBasket = view.findViewById(R.id.btnGoBasket);
        goBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the basket is not empty, and navigate to the basket fragment
                if (!DataManager.BasketList.isEmpty())
                    Navigation.findNavController(view).navigate(R.id.action_main_to_basket);
            }
        });

        return view;
    }
}
