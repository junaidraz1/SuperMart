package com.example.s3supermart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3supermart.Model.OrdersExpandableLayoutModelClass;
import com.example.s3supermart.R;

import java.util.List;

public class OrdersExpandableLayoutAdapter extends RecyclerView.Adapter<OrdersExpandableLayoutAdapter.CustomViewHolder> {


    private final List<OrdersExpandableLayoutModelClass> itemList;
    private final Context context;

    /*
        public OrdersExpandableLayoutAdapter(Context context, List<AppointmentChildModelClass> itemList) {
            this.context = context;
            this.itemList = itemList;
        }*/
    public OrdersExpandableLayoutAdapter(Context context, List<OrdersExpandableLayoutModelClass> subItemList) {
        this.itemList = subItemList;
        this.context = context;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_order_recyclerview, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        int pos = position + 3;
        holder.tv_productName.setText(+pos + "- " + itemList.get(position).getProductName());
        holder.tv_productPrice.setText(itemList.get(position).getProductPrice());
        holder.iv_product.setImageResource(itemList.get(position).getProductImage());


              /*  if (itemList.get(position).getTest()=="")
                {
                    AppointmentParentAdapter.CustomViewHolder.imageView.setVisibility(View.GONE);
                    holder.Test_Tv.setVisibility(View.GONE);
                }*/
    }

    @Override
    public int getItemCount() {
        return itemList.size();
        //return 0;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        TextView tv_productName, tv_productPrice;
        ImageView iv_product;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tv_productName = view.findViewById(R.id.tv_productName);
            tv_productPrice = view.findViewById(R.id.tv_productPrice);
            iv_product = view.findViewById(R.id.iv_productOrder);

        }
    }
}
