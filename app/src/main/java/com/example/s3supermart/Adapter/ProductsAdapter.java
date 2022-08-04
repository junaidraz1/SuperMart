package com.example.s3supermart.Adapter;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3supermart.Model.ProductsClass;
import com.example.s3supermart.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.CustomViewHolder> {

    private final List<ProductsClass> productsList;
    private final Context context;
    String Tv_Inc_Dec;

    public ProductsAdapter(Context context, List<ProductsClass> productsList) {
        this.context = context;
        this.productsList = productsList;

    }

    @NonNull
    @Override
    public ProductsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_product_items, parent, false);
        return new ProductsAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.CustomViewHolder holder, int position) {
        holder.tv_ProductName.setText(productsList.get(position).getProductName());
        holder.tv_productQuantity.setText(productsList.get(position).getProductQuantity());
        holder.tv_productPrice.setText(productsList.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    /* private void showMenu(View v) {
         PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
         popupMenu.getMenuInflater().inflate(R.menu.menu_result_entry, popupMenu.getMenu());
         popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 if (item.getItemId() == R.id.DataEntry_RE) {

                     Intent intent = new Intent(context, LabQueueDataEntryActivity.class);
                     context.startActivity(intent);

                 }

                 if (item.getItemId() == R.id.BarCode_RE) {

                 }
                 if (item.getItemId() == R.id.CallBack_RE) {
                     AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                     View view = LayoutInflater.from(context).inflate(R.layout.dialog_callback, null);
                     builder.setView(view);
                     AlertDialog dialog = builder.create();
                     dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                     ImageView IV_Close = view.findViewById(R.id.iv_closeCallback);
                     Button Btn_Callback = view.findViewById(R.id.btn_Callback);

                     IV_Close.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dialog.dismiss();
                         }
                     });
                     Btn_Callback.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dialog.dismiss();
                         }
                     });
                     dialog.show();
                 }
                 if (item.getItemId() == R.id.UpdateFlightDetails_RE) {
                     AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                     View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_flight_details, null);
                     builder.setView(view);
                     AlertDialog dialog = builder.create();
                     dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                     ImageView IV_Close = view.findViewById(R.id.iv_closeFlightDetail);
                     Button Btn_Cancel, Btn_Update;
                     Btn_Cancel = view.findViewById(R.id.btn_CancelFD);
                     Btn_Update = view.findViewById(R.id.btn_updateFD);

                     IV_Close.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dialog.dismiss();
                         }
                     });
                     Btn_Cancel.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dialog.dismiss();
                         }
                     });
                     Btn_Update.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dialog.dismiss();
                         }
                     });
                     dialog.show();
                 }
                 return true;
             }
         });
         popupMenu.show();
     }
 */
    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        TextView tv_ProductName, tv_productQuantity, tv_productPrice, tv_inc_dec;
        Button btn_addToCart, btn_increment, btn_decrement;
        LinearLayout ll_addToCart;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            tv_ProductName = view.findViewById(R.id.tv_productName);
            tv_productQuantity = view.findViewById(R.id.tv_productQuantity);
            tv_productPrice = view.findViewById(R.id.tv_productPrice);
            btn_addToCart = view.findViewById(R.id.btn_AddToCart);
            ll_addToCart = view.findViewById(R.id.ll_AddToCart);
            tv_inc_dec = view.findViewById(R.id.tv_inc_dec);
            btn_increment = view.findViewById(R.id.btn_increment);
            btn_decrement = view.findViewById(R.id.btn_decrement);

           /* layout_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMenu(v);
                }
            });*/

            btn_addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_addToCart.setVisibility(View.VISIBLE);
                    btn_addToCart.setVisibility(View.GONE);
                    tv_inc_dec.setText("1");
                    //  Toast.makeText(context, "Add to Cart Button Clicked", Toast.LENGTH_SHORT).show();
                }
            });
            btn_decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tv_Inc_Dec = tv_inc_dec.getText().toString();
                    if (tv_inc_dec.getText().toString().equals("1")) {
                        btn_addToCart.setVisibility(View.VISIBLE);
                        ll_addToCart.setVisibility(View.GONE);
                        // Toast.makeText(context, "Button Decriment Clicked", Toast.LENGTH_SHORT).show();
                    } else {
                        quantityDecrement();
                    }
                }
            });

            btn_increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantityIncrement();
                }
            });
        }

        private void quantityIncrement() {
            // Get the value of the text view
            String countString = tv_inc_dec.getText().toString();
            // Convert value to a number and increment it
            int totalStock = 10;
            int qty = parseInt(countString);
            if (qty < totalStock) {
                qty++;
                String num = String.valueOf(qty);
                // Display the new value in the text view.
                tv_inc_dec.setText(num);

            } else {
                Toast.makeText(context, "No more quantity available", Toast.LENGTH_SHORT).show();

            }
        }

        private void quantityDecrement() {
            // Get the value of the text view
            String countString = tv_inc_dec.getText().toString();
            // Convert value to a number and increment it
            int qty = parseInt(countString);
            qty--;
            String num = String.valueOf(qty);
            // Display the new value in the text view.
            tv_inc_dec.setText(num);

        }
    }
}
