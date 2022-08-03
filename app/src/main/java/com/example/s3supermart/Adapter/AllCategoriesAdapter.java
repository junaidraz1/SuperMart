package com.example.s3supermart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3supermart.Model.AllCategoriesClass;
import com.example.s3supermart.R;

import java.util.List;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.CustomViewHolder>{

    private final List<AllCategoriesClass> categoriesList;
    private final Context context;

    public AllCategoriesAdapter(Context context, List<AllCategoriesClass> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;

    }

    @NonNull
    @Override
    public AllCategoriesAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_categories_items, parent, false);
        return new AllCategoriesAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoriesAdapter.CustomViewHolder holder, int position) {

    /*    holder.tv_Prescribed.setText(categoriesList.get(position).getPrescribedBy());
        holder.tv_dateTime.setText(categoriesList.get(position).getDateTime());
        // holder.tv_Sr.setText(patientDataList.get(position).getSr());
        holder.tv_patientName.setText(categoriesList.get(position).getPatientName());
        holder.tv_MrNo.setText(categoriesList.get(position).getMRNo());
        holder.tv_visitNo.setText(categoriesList.get(position).getVisitNo());
        holder.tv_labNo.setText(categoriesList.get(position).getLabNo());
        holder.tv_services.setText(categoriesList.get(position).getServices());*/
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
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

        TextView tv_Prescribed, tv_dateTime, tv_Sr, tv_patientName, tv_MrNo, tv_visitNo, tv_labNo, tv_services;
        ImageView IV_Menu;
        LinearLayout layout_menu;
        Button Btn_DataEntry, Btn_ClinicalNotes;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

/*
            tv_Prescribed = view.findViewById(R.id.TV_PrescribedBy_RE);
            tv_dateTime = view.findViewById(R.id.TV_DateTime_RE);
            //tv_Sr = view.findViewById(R.id.Tv_SrNo_RE);
            tv_patientName = view.findViewById(R.id.Tv_PatientName_RE);
            tv_MrNo = view.findViewById(R.id.TV_MRNO_RE);
            tv_visitNo = view.findViewById(R.id.TV_VisitNo_RE);
            tv_labNo = view.findViewById(R.id.TV_LabNo_RE);
            tv_services = view.findViewById(R.id.TV_Services_RE);
            layout_menu = view.findViewById(R.id.LL_MenuIcon_RE);
*/

           /* layout_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMenu(v);
                }
            });*/
        }
    }
}
