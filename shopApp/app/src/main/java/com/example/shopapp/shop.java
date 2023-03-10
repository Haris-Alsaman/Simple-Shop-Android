package com.example.shopapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class shop extends Fragment {

    View view;
    RecyclerView productsView;  // RecyclerView for displaying the list of products

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);

        // Create an instance of the ProductsAdapter with the list of products and the current context
        ProductsAdapter productsAdapter = new ProductsAdapter(DataManager.productsList, view.getContext());

        // Find the RecyclerView in the layout and configure it
        productsView = view.findViewById(R.id.producsView);
        productsView.setHasFixedSize(true);
        productsView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        productsView.setAdapter(productsAdapter);

        return view;
    }
}




