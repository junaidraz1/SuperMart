package com.example.s3supermart.Fragment;

import static com.example.s3supermart.Activity.HomeActivity.currentFragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3supermart.Activity.HomeActivity;
import com.example.s3supermart.Adapter.AllCategoriesAdapter;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Model.AllCategoriesClass;
import com.example.s3supermart.R;
import com.example.s3supermart.Utils.Utility;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    CarouselView carouselView;
    TextView tv_greetMsg, tv_location;
    ImageView iv_edit, iv_menu;
    Utility utility;
    HomeActivity homeActivity;
    CardView cv_grocery;
    LinearLayout ll_homeFragbar;
    int[] sampleImages = {R.drawable.offer1, R.drawable.offer2,
            R.drawable.logo, R.drawable.slider1, R.drawable.offer3};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //intialising id's to variables
        carouselView = view.findViewById(R.id.carouselView);
        tv_greetMsg = view.findViewById(R.id.tv_gd_morning);
        cv_grocery = view.findViewById(R.id.cv_groceryCategory);
        ll_homeFragbar = view.findViewById(R.id.ll_topbarHome);
        tv_location = view.findViewById(R.id.tv_location);
        iv_edit = view.findViewById(R.id.iv_editLocation);
        iv_menu = view.findViewById(R.id.iv_homeMenu);

        //intialising classes
        utility = new Utility();
        homeActivity = new HomeActivity();

        tv_location.setSelected(true);
        tv_location.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        //method that contains implementation of greeting message
        greetUser();

        clickListeners();

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


        // Inflate the layout for this fragment
        return view;
    }

    public void clickListeners() {
        cv_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() != null) {
                    currentFragment = new GroceryFragment();
                    ((HomeActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, currentFragment,
                            currentFragment.getClass().getSimpleName()).commit();
                    ((HomeActivity) getContext()).changetopBar();

                }
            }
        });

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() != null) {
                    DialogHandler.homeMenu(getContext());
                }
            }
        });
    }

    //method to display greeting message on home screen
    public void greetUser() {
        if (utility.getWishingMessage() == null || utility.getWishingMessage().equals("")) {
            tv_greetMsg.setText("Good Morning");

        } else {
            tv_greetMsg.setText(utility.getWishingMessage());
        }
    }

    //image listener for carousel view
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}