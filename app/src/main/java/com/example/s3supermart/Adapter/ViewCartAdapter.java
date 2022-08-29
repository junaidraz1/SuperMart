package com.example.s3supermart.Adapter;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3supermart.Model.ProductsClass;
import com.example.s3supermart.Model.ViewCartClass;
import com.example.s3supermart.R;

import java.util.List;

public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.CustomViewHolder> {

    private final List<ViewCartClass> productsList;
    private final Context context;
    String Tv_Counter;

    public ViewCartAdapter(Context context, List<ViewCartClass> productsList) {
        this.context = context;
        this.productsList = productsList;
    }
    @NonNull
    @Override
    public ViewCartAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_viewcart, parent, false);
        return new ViewCartAdapter.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewCartAdapter.CustomViewHolder holder, int position) {
        holder.tv_ProductName.setText(productsList.get(position).getProductName());
        holder.tv_productQuantity.setText(productsList.get(position).getProductQuantity());
        holder.tv_productPrice.setText(productsList.get(position).getProductPrice());
        holder.iv_productImage.setImageResource((productsList.get(position).getProductImageUri()));
    }
    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        TextView tv_ProductName, tv_productQuantity, tv_productPrice, tv_quantityCounter;
        LinearLayout ll_addToCart,ll_increment,ll_decrement,ll_deleteProduct;
        ImageView iv_delete,iv_productImage;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            tv_ProductName = view.findViewById(R.id.tv_productName);
            tv_productQuantity = view.findViewById(R.id.tv_productQuantity);
            tv_productPrice = view.findViewById(R.id.tv_productPrice);
            ll_addToCart = view.findViewById(R.id.ll_AddToCart);
            tv_quantityCounter = view.findViewById(R.id.tv_quantityCounter);
            ll_increment = view.findViewById(R.id.ll_increment);
            ll_decrement = view.findViewById(R.id.ll_decrement);
            ll_deleteProduct = view.findViewById(R.id.ll_deleteProduct);
            iv_productImage = view.findViewById(R.id.iv_productImage);

            ll_deleteProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem();
                }
            });

            ll_decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tv_Counter = tv_quantityCounter.getText().toString();
                    if (tv_quantityCounter.getText().toString().equals("1")) {
                     //   ll_addToCart.setVisibility(View.GONE);
                        // Toast.makeText(context, "Button Decriment Clicked", Toast.LENGTH_SHORT).show();
                        deleteItem();
                    } else {
                        quantityDecrement();
                    }
                }
            });

            ll_increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantityIncrement();
                }
            });
        }

        private void quantityIncrement() {
            // Get the value of the text view
            String countString = tv_quantityCounter.getText().toString();
            // Convert value to a number and increment it
            int totalStock = 10;
            int qty = parseInt(countString);
            if (qty < totalStock) {
                qty++;
                String num = String.valueOf(qty);
                // Display the new value in the text view.
                tv_quantityCounter.setText(num);

            } else {
                Toast.makeText(context, "No more quantity available", Toast.LENGTH_SHORT).show();

            }
        }
        private void quantityDecrement() {
            // Get the value of the text view
            String countString = tv_quantityCounter.getText().toString();
            // Convert value to a number and increment it
            int qty = parseInt(countString);

            if (qty>1){
                qty--;
            }
            String num = String.valueOf(qty);
            // Display the new value in the text view.
            tv_quantityCounter.setText(num);
        }
        private void deleteItem() {
            productsList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(),productsList.size());
        }
    }
}
