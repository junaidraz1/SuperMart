package com.example.s3supermart.Adapter;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3supermart.Model.CheckOutClass;
import com.example.s3supermart.Model.ViewCartClass;
import com.example.s3supermart.R;

import java.util.List;

public class CheckOuutAdapter extends RecyclerView.Adapter<CheckOuutAdapter.CustomViewHolder> {

    private final List<CheckOutClass> productsList;
    private final Context context;
    String Tv_Counter;

    public CheckOuutAdapter(Context context, List<CheckOutClass> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public CheckOuutAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_checkout_products, parent, false);
        return new CheckOuutAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOuutAdapter.CustomViewHolder holder, int position) {
        holder.tv_ProductName.setText(productsList.get(position).getProductName());
        holder.tv_productQuantity.setText(productsList.get(position).getProductQuantity());
        holder.tv_productPrice.setText(productsList.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        TextView tv_ProductName, tv_productQuantity, tv_productPrice;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            tv_ProductName = view.findViewById(R.id.tv_checkOut_ProductName);
            tv_productQuantity = view.findViewById(R.id.tv_checkOut_ProductQuantity);
            tv_productPrice = view.findViewById(R.id.tv_checkOut_ProductPrice);
        }
    }
}
