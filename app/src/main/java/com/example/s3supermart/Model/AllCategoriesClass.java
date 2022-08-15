package com.example.s3supermart.Model;

import android.widget.ImageView;

public class AllCategoriesClass {

    String categoryName;
    int categoryPicture;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(int categoryPicture) {
        this.categoryPicture = categoryPicture;
    }


    public AllCategoriesClass(String categoryName, int categoryPicture) {
        this.categoryName = categoryName;
        this.categoryPicture = categoryPicture;
    }


}
