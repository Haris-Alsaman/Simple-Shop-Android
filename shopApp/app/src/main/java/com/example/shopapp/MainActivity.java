package com.example.shopapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.VolleyError;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductFetcher.ProductListListener {
    private ProductFetcher productFetcher;

    // Define a constant to use as a request code when asking for write external storage permission
    private final static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize a new ProductFetcher instance
        productFetcher = new ProductFetcher(this);

        // Call fetchProductList to get the list of products from the API
        productFetcher.fetchProductList(this);

        // Request write external storage permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            // Permission already granted, show a toast message
            Toast.makeText(this, "Write external storage permission granted.", Toast.LENGTH_LONG).show();
        }
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, show a toast message
                    Toast.makeText(this, "Write external storage permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    // Permission denied, show a toast message
                    Toast.makeText(this, "Write external storage permission denied.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    // Callback method called when the product list is successfully fetched from the API
    @Override
    public void onProductListReceived(List<Products> productsList) {
        // Save the list of products to the DataManager class for later use
        DataManager.productsList = productsList;
    }

    // Callback method called when an error occurs while fetching the product list from the API
    @Override
    public void onProductListError(VolleyError error) {
        // Show a toast message with the error details
        Toast.makeText(this, "Error occurred while fetching product list: " + error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
