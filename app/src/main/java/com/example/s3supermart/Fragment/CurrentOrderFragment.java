package com.example.s3supermart.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3supermart.Adapter.OrdersAdapter;
import com.example.s3supermart.Model.OrdersExpandableLayoutModelClass;
import com.example.s3supermart.Model.OrdersModelClass;
import com.example.s3supermart.R;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrderFragment extends Fragment {

    RecyclerView recyclerView;
    OrdersAdapter ordersAdapter;
    List<OrdersModelClass> productList;

    public CurrentOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);

        recyclerView = view.findViewById(R.id.rv_currentOrders);
        //intiailising arraylist
        productList = new ArrayList<>();

        setAdapter();
        return view;
    }

    public void setAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ordersAdapter = new OrdersAdapter(getContext(),ProductList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ordersAdapter);
    }

    /* Method containg a list of parent model class items*/
    private List<OrdersModelClass> ProductList() {
        OrdersModelClass item = new OrdersModelClass(
                "Olpers",
                "200",
                "", "", R.drawable.olpers_milk_1_litre,
                R.drawable.ic_home_icon_white,classList());
       OrdersModelClass item1 = new OrdersModelClass(
                "Olpers 250 ml",
                "55",
                "Olpers 1 liter", "280", R.drawable.olpers_milk_250ml,
                R.drawable.olpers_milk_1_litre,classList());
        OrdersModelClass item2 = new OrdersModelClass(
                "Dalda Oil",
                "600", "Sugar", "300",R.drawable.oil_ghee,
                R.drawable.tea_coffee_and_sugar,
                childItemList());
        OrdersModelClass item3 = new OrdersModelClass(
                "Chawal",
                "500", "Milk pack", "200",
                 R.drawable.aata_daal_chawal,
                 R.drawable.milk_cheese_and_yogurt,
                childItemList1());

        productList.add(item);
        productList.add(item1);
        productList.add(item2);
        productList.add(item3);
        return productList;
    }

    /* method that contains a list of child model class data having only one items*/
    public List<OrdersExpandableLayoutModelClass> childItemList() {
        List<OrdersExpandableLayoutModelClass> subItemList = new ArrayList<>();
        OrdersExpandableLayoutModelClass subItem = new OrdersExpandableLayoutModelClass("Atta ",
                "1500",R.drawable.atta_daal_and_chaawal);
        subItemList.add(subItem);
        return subItemList;
    }

    /* method that contains a list of child model class data having two items*/
    public List<OrdersExpandableLayoutModelClass> childItemList1() {
        List<OrdersExpandableLayoutModelClass> subItemList = new ArrayList<>();
        OrdersExpandableLayoutModelClass subItem = new OrdersExpandableLayoutModelClass("Atta ",
                "1500",R.drawable.atta_daal_and_chaawal);
        OrdersExpandableLayoutModelClass subItem1 = new OrdersExpandableLayoutModelClass("Baby Food ",
                "50",R.drawable.baby_food);
        OrdersExpandableLayoutModelClass subItem2 = new OrdersExpandableLayoutModelClass("Baby Food ",
                "50",R.drawable.baby_food);
        subItemList.add(subItem);
        subItemList.add(subItem1);
        subItemList.add(subItem2);
        return subItemList;
    }

    /* method that contains a list of child model class data having no items*/
    public List<OrdersExpandableLayoutModelClass> classList() {
        List<OrdersExpandableLayoutModelClass> subItemList = new ArrayList<>();
       /* AppointmentChildModelClass subItem3 = new AppointmentChildModelClass("");
        subItemList.add(subItem3);*/
        return subItemList;

    }
}