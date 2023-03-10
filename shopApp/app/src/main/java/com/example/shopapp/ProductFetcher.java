package com.example.shopapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductFetcher {
    private final String apiUrl = "https://63f73b1b833c7c9c607f5f1b.mockapi.io/products";
    private RequestQueue queue;
    private Gson gson;
    private Type productListType;

    public ProductFetcher(Context context) {
        queue = Volley.newRequestQueue(context);
        gson = new Gson();
        productListType = new TypeToken<ArrayList<Products>>(){}.getType();
    }

    public interface ProductListListener {
        void onProductListReceived(List<Products> productsList);
        void onProductListError(VolleyError error);
    }

    // fetches the product list from the API
    public void fetchProductList(final ProductListListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // parse the response and convert it to a list of Products objects
                        List<Products> productList = gson.fromJson(response.toString(), productListType);
                        listener.onProductListReceived(productList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onProductListError(error);
                    }
                });
        // add the request to the request queue
        queue.add(jsonArrayRequest);
    }
}
