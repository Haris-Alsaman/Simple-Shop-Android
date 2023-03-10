package com.example.shopapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    // Define a List of Products objects to hold the list of products that will be displayed in the RecyclerView.
// This list is declared as static so it can be accessed from the fragment that initializes the adapter.
    static List<Products> productsList;

    // Define a context object to hold the context in which the RecyclerView is displayed.
    private static Context context;

    // Define a setter method to set the productsList object with the list of products to be displayed.
    public void setProductsList(List<Products> productsList) {
        ProductsAdapter.productsList = productsList;

        // Notify the adapter that the data has changed and the RecyclerView should be updated.
        notifyDataSetChanged();
    }

    // Define the constructor for the ProductsAdapter class that initializes the productsList and context objects.
    public ProductsAdapter(List<Products> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    // Define the onCreateViewHolder method that inflates the layout for the product list item and returns a new ViewHolder object.
    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productsshow,parent,false);

        return new ViewHolder(itemView);
    }

    // Define the onBindViewHolder method that binds the product data to the views in the ViewHolder object.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the text for the product name TextView from the Products object at the current position.
        holder.productsname.setText(productsList.get(position).getName());

        // Set the text for the product description TextView from the Products object at the current position.
        holder.proudctsdes.setText(productsList.get(position).getDescription());

        // Set the text for the product price TextView from the Products object at the current position.
        holder.productsprice.setText(productsList.get(position).getPrice()+" TL");

        // Use the Picasso library to load the product image from the URL stored in the Products object.
        Picasso.get().load(productsList.get(position).getImage()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("data","data");
            }
            @Override
            public void onError(Exception e) {
                // If the image is not cached, load it from the network and cache it for future use.
                Picasso.get().load(productsList.get(holder.getAdapterPosition()).getImage()).into(holder.imageView);
            }
        });

        // Define an onClickListener for the item view that sets the productNumber variable in the DataManager class
        // to the current item position and navigates to the product details fragment.
        holder.itemView.setOnClickListener(view -> {
            DataManager.productNumber = holder.getAdapterPosition();
            Navigation.findNavController(view).navigate(R.id.action_shop_to_theProduct2);
        });
    }

    // Define the getItemCount method that returns the number of items in the productsList.
    @Override
    public int getItemCount() {
        return productsList.size();
    }

    // Define the ViewHolder class that holds the views for the product list item.
    public static class ViewHolder extends RecyclerView.ViewHolder  {
        TextView productsname,productsprice,proudctsdes;
        public ImageView imageView;
        CardView cardView;

        // Define the constructor for the ViewHolder class that initializes the views in the item layout.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             productsname = itemView.findViewById(R.id.productsName);
             productsprice = itemView.findViewById(R.id.productsPrice);
             proudctsdes = itemView.findViewById(R.id.productsDes);
            imageView = itemView.findViewById(R.id.imageView);
        }


    }
}
