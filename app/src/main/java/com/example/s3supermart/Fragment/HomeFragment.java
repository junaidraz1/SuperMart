package com.example.s3supermart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s3supermart.Adapter.AllCategoriesAdapter;
import com.example.s3supermart.Model.AllCategoriesClass;
import com.example.s3supermart.R;
import com.example.s3supermart.Utils.Utility;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    CarouselView carouselView;
    TextView tv_greetMsg;
    AllCategoriesAdapter allCategoriesAdapter;
    Utility utility;
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

        //intialising classes
        utility = new Utility();

        //method that contains implementation of greeting message
        greetUser();

        //to set items to recyclerview for product category
       // setCategory();

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


        // Inflate the layout for this fragment
        return view;
    }

    //method to display greeting message on home screen
    public void greetUser() {
        if (utility.getWishingMessage() == null) {
            tv_greetMsg.setText("Good Morning");

        } else {
            tv_greetMsg.setText(utility.getWishingMessage());
        }
    }

    //method to add items to category recycler view
    public List<AllCategoriesClass> categoryList() {

        List<AllCategoriesClass> allCategoriesList = new ArrayList<>();

        AllCategoriesClass listItem1 = new AllCategoriesClass("Dairy Product", R.drawable.olpers_milk_1_litre);
        AllCategoriesClass listItem2 = new AllCategoriesClass("Fruits and Vegetables", R.drawable.karaila);
        AllCategoriesClass listItem3 = new AllCategoriesClass("Noodles and Sauces", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem4 = new AllCategoriesClass("Snacks", R.drawable.lays);
        AllCategoriesClass listItem5 = new AllCategoriesClass("Ice Cream & Chocolates", R.drawable.pancake_serup);
        AllCategoriesClass listItem6 = new AllCategoriesClass("Beverages", R.drawable.pancake_serup);
        AllCategoriesClass listItem7 = new AllCategoriesClass("Personal Care", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem8 = new AllCategoriesClass("Baby Care", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem9 = new AllCategoriesClass("Oil and Ghee", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem10 = new AllCategoriesClass("Atta, Daal aur Chawal", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem11 = new AllCategoriesClass("Laundry", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem12 = new AllCategoriesClass("Tea, Coffee, Sugar", R.drawable.milk_cheese_and_yogurt);
        AllCategoriesClass listItem13 = new AllCategoriesClass("Baby Food", R.drawable.milk_cheese_and_yogurt);

        allCategoriesList.add(listItem1);
        allCategoriesList.add(listItem2);
        allCategoriesList.add(listItem3);
        allCategoriesList.add(listItem4);
        allCategoriesList.add(listItem5);
        allCategoriesList.add(listItem6);
        allCategoriesList.add(listItem7);
        allCategoriesList.add(listItem8);
        allCategoriesList.add(listItem9);
        allCategoriesList.add(listItem10);
        allCategoriesList.add(listItem11);
        allCategoriesList.add(listItem12);
        allCategoriesList.add(listItem13);

        return allCategoriesList;
    }

    //image listener for carousel view
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}