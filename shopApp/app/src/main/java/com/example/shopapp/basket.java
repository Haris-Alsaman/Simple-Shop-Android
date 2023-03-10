package com.example.shopapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class basket extends Fragment {
    View view;

    // This method inflates the fragment's layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_basket, container, false);

        // Get the context of the view
        Context context = view.getContext();

        // Create an adapter for the products in the basket
        InBasketProductsAsapter InBasketProductsAsapter = new InBasketProductsAsapter(DataManager.BasketList, view.getContext());

        // Get the RecyclerView that displays the products in the basket
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.basketProductview);

        // Set the properties of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(InBasketProductsAsapter);

        // Get the checkout button and set an onClickListener for it
        Button basketchekOut = view.findViewById(R.id.basketchekOut);
        basketchekOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the basket is not empty, display the total price and clear the basket
                if (!DataManager.BasketList.isEmpty()) {
                    Toast.makeText(context, "Successfully Total price is :" + DataManager.BasketTotalPrice + " TL", Toast.LENGTH_LONG).show();
                    DataManager.BasketList.clear();
                    InBasketProductsAsapter.setProductsList(DataManager.BasketList);
                }
            }
        });

        // Return the inflated view
        return view;
    }

    }
