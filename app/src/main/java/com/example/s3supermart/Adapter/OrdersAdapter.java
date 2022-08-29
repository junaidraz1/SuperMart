package com.example.s3supermart.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.s3supermart.Model.OrdersModelClass;
import com.example.s3supermart.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.CustomViewHolder> {

  //  public static ItemClickListener itemClickListener;
    private final List<OrdersModelClass> productList;
    private final Context context;

    public OrdersAdapter(Context context, List<OrdersModelClass> itemList/*, ItemClickListener itemClickListener*/) {
        this.context = context;
        this.productList = itemList;
      //  OrdersAdapter.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_orders, parent, false);
        return new CustomViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_productName1.setText("1- " + productList.get(position).getProductName1());
        holder.tv_productName2.setText("2- " + productList.get(position).getProductName2());
        holder.tv_productPrice1.setText(productList.get(position).getProductPrice1());
        holder.tv_productPrice2.setText(productList.get(position).getProductPrice2());
        holder.iv_product1.setImageResource(productList.get(position).getProductImage1());
        holder.iv_product2.setImageResource(productList.get(position).getProductImage2());

        holder.iv_rotate.setVisibility(View.VISIBLE);
        if (productList.get(position).getProductName2().equals("")) {
            holder.tv_productName2.setVisibility(View.GONE);
            holder.iv_product2.setVisibility(View.GONE);
            holder.ll_price2.setVisibility(View.GONE);
            holder.view_order.setVisibility(View.GONE);
        }
    /*    if (productList.get(position).getTest3().equals("")) {
            holder.tv_test3.setVisibility(View.GONE);
        }*/
        if (productList.get(position).getSubItemList().isEmpty()) {
            holder.iv_rotate.setVisibility(View.GONE);
        }

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.recyclerView.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(productList.get(position).getSubItemList().size());
        OrdersExpandableLayoutAdapter subItemAdapter = new OrdersExpandableLayoutAdapter(context, productList.get(position).getSubItemList());
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(subItemAdapter);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View view;

        TextView tv_productName1, tv_productName2, tv_productPrice1, tv_productPrice2;
        RecyclerView recyclerView;
        ExpandableLayout expandableLayout;
        LinearLayout layout_expand,ll_price2;
        ImageView iv_rotate,iv_product1,iv_product2;
        View view_order;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            tv_productName1 = view.findViewById(R.id.tv_productName1);
            tv_productName2 = view.findViewById(R.id.tv_productName2);
            tv_productPrice1 = view.findViewById(R.id.tv_productPrice1);
            tv_productPrice2 = view.findViewById(R.id.tv_productPrice2);
            iv_product1 = view.findViewById(R.id.iv_productOrder1);
            iv_product2 = view.findViewById(R.id.iv_productOrder2);
            ll_price2 = view.findViewById(R.id.ll_price2);
            view_order = view.findViewById(R.id.view_orders);
            expandableLayout = view.findViewById(R.id.expandableLayout_Order);
            layout_expand = view.findViewById(R.id.ll_expanded_Orders);
            iv_rotate = view.findViewById(R.id.iv_rotate_Orders);
            recyclerView = view.findViewById(R.id.rv_expandableLayout_Order);

            recyclerView.setVisibility(View.GONE);
            iv_rotate.setVisibility(View.VISIBLE);

            //clicklistener for Expandable layout that will expand and collapse on a click
            layout_expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandableLayout.isExpanded()) {
                        expandableLayout.collapse();
                        iv_rotate.animate().rotation(360).setDuration(1000);
                    } else {
                        expandableLayout.expand();
                        recyclerView.setVisibility(View.VISIBLE);
                        iv_rotate.animate().rotation(180).setDuration(1000);
                    }
                }
            });
           /* layout_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    View view = LayoutInflater.from(context).inflate(R.layout.dialog_userinfo_bottomsheet, null);
                    builder.setView(view);
                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    LinearLayout layout_close;
                    Button btn_ok;
                    TextView tv_patientName, tv_mobileNo, tv_email, tv_location;

                    layout_close = view.findViewById(R.id.ll_closeUserinfoBottomsheet);
                    tv_patientName = view.findViewById(R.id.tv_patientNameBottomsheetDialog);
                    tv_mobileNo = view.findViewById(R.id.tv_mobileNoBottomsheetDialog);
                    tv_email = view.findViewById(R.id.tv_emailBottomsheetDialog);
                    tv_location = view.findViewById(R.id.tv_patientLocationBottomsheetDialog);
                    btn_ok = view.findViewById(R.id.Btn_OkUserInfoBottomsheet);

                    tv_patientName.setText(itemList.get(getPosition()).getName());
                    tv_mobileNo.setText(itemList.get(getPosition()).getMobileNo());
                    tv_email.setText(itemList.get(getPosition()).getEmail());
                    tv_location.setText(itemList.get(getPosition()).getLocation());

                    layout_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
            view.setOnClickListener(this);
        }*/

   /*     @Override
        public void onClick(View v) {

            itemClickListener.onClick(v, getAdapterPosition());
        }*/

        }

        @Override
        public void onClick(View v) {

        }
    }
}
