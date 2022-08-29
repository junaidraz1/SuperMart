package com.example.s3supermart.Fragment;

import static com.example.s3supermart.Activity.HomeActivity.currentFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.Adapter.ProductsAdapter;
import com.example.s3supermart.Adapter.ViewCartAdapter;
import com.example.s3supermart.Model.ProductsClass;
import com.example.s3supermart.Model.ViewCartClass;
import com.example.s3supermart.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class ViewCartFragment extends Fragment {

    RecyclerView recyclerView;
    ViewCartAdapter viewCartAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<ViewCartClass> viewCartClassList;
    TextView tv_topBar, tv_itemsCounterBottomSheet, tv_emptyCart;
    LinearLayout ll_bottomBar;
    RelativeLayout rl_titleBottomshet;
    Button btn_checkOut;
    BottomSheetBehavior<View> bottomSheetBehavior;


    public ViewCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_cart, container, false);

        tv_itemsCounterBottomSheet = view.findViewById(R.id.tv_itemsCounterBottomSheet);
        tv_emptyCart = view.findViewById(R.id.tv_emptyCart);
        recyclerView = view.findViewById(R.id.rv_viewCart);
        btn_checkOut = view.findViewById(R.id.btn_checkout);
        recyclerView.setHasFixedSize(true);
        View BottomsheetView = view.findViewById(R.id.ll_bottomSheetView);
        rl_titleBottomshet = view.findViewById(R.id.Rl_titleBottomsheet);
        tv_topBar = getActivity().findViewById(R.id.tv_titleTopBar);
        tv_topBar.setText("View Shopping Cart");
        ll_bottomBar = getActivity().findViewById(R.id.ll_bottomBar);
        ll_bottomBar.setVisibility(View.GONE);
        ((HomeActivity) getContext()).topBarWithBackIcon();

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        viewCartClassList = new ArrayList<>();

        //Adding Data into ArrayList
        viewCartClassList.add(new ViewCartClass("Olpers Full Cream Milk ", "250 ml", "Rs.55", R.drawable.olpers_milk_250ml));
        viewCartClassList.add(new ViewCartClass("Cake Cup ", "", "Rs.200", R.drawable.cakecup));
        viewCartClassList.add(new ViewCartClass("7up bottle ", "1.5 Litre", "Rs.130", R.drawable.beverage));
        viewCartAdapter = new ViewCartAdapter(getContext(), viewCartClassList);
        recyclerView.setAdapter(viewCartAdapter);


        if (viewCartClassList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tv_emptyCart.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tv_emptyCart.setVisibility(View.GONE);
        }

                      /*  it is use to take the instance of the
         behavior from the layout params of the View instance*/
        bottomSheetBehavior = BottomSheetBehavior.from(BottomsheetView);

        /* Sets the bottom sheet height with the
         value set on the peekHeight attribute*/
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        //used to handle behaviour i.e. expand or collapsed of bottom sheet
        rl_titleBottomshet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        btn_checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment = new CheckOutFragment();
                ((HomeActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, currentFragment,
                        currentFragment.getClass().getSimpleName()).commit();
            }
        });
        return view;

    }
}