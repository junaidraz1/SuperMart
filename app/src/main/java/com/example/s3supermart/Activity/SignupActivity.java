package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.s3supermart.Adapter.ViewPagerAdapter;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Helper.PrefsManager;
import com.example.s3supermart.R;
import com.google.android.material.tabs.TabLayout;
import com.xwray.passwordview.PasswordView;

public class SignupActivity extends AppCompatActivity {

    EditText et_fname, et_lname, et_email;
    PasswordView pv_password, pv_confirmPass;
    Button btn_regUser;
    RelativeLayout rl_back;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    LinearLayout layout_back, layout_menu;
    RelativeLayout rl_signin;
    String currentAddress = "";
    String appartmentInfo = "";
    PrefsManager prefsManager;
    TextView tv_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //intialising ids to  variables
   /*     viewPager = findViewById(R.id.viewpagerSignUp);
        tabLayout = findViewById(R.id.tab_layoutSignUp);*/
        et_fname = findViewById(R.id.et_fName);
        et_lname = findViewById(R.id.et_lName);
        et_email = findViewById(R.id.et_email);
        tv_address = findViewById(R.id.tv_location);
        pv_password = findViewById(R.id.pv_password);
        pv_confirmPass = findViewById(R.id.pv_confirmPassword);
        btn_regUser = findViewById(R.id.btn_regUser);
        rl_back = findViewById(R.id.rl_back);
        rl_signin = findViewById(R.id.rl_signin);

        prefsManager = new PrefsManager(this);
       /* currentAddress = prefsManager.getCURRENTLOCATION_KEY();
        appartmentInfo = prefsManager.getAPARTMENTINFO_KEY();*/
        //  tabAndViewPager();
        //contains implementation of all the click listeners involve
        clickListeners();


        currentAddress = getIntent().getStringExtra("CURRENT_ADDRESS");
        appartmentInfo = getIntent().getStringExtra("APPARTMENT_INFO");
        if (currentAddress == null && appartmentInfo == null){
            tv_address.setText("Enter Address");
            tv_address.setTextColor(ContextCompat.getColor(this, R.color.hint));
        }
        else {
            tv_address.setText(appartmentInfo + " " + currentAddress);
            tv_address.setTextColor(ContextCompat.getColor(this, R.color.dusk));
        }
    }

    public void clickListeners() {

        //contains implementation of functionality when user clicks on register button
        btn_regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
                    DialogHandler.customDialog(SignupActivity.this, "Registration", "User Registered Successfully \n \n Please Login to Continue");
                }
            }
        });

        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        rl_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, GoogleMapActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

    //method to validate fields for signup
    public boolean validation() {

        String firstName = et_fname.getText().toString();
        String email = et_email.getText().toString();
        String password = pv_password.getText().toString();
        String con_pass = pv_confirmPass.getText().toString();

        if (firstName.equals("")) {
            et_fname.setError("First name is required");
            return false;

        } else if (email.equals("")) {
            et_email.setError("Email is required");
            return false;

        } else if (password.equals("")) {
            DialogHandler.customDialog(SignupActivity.this, "Reset Password", "Password is required");
            return false;

        } else if (con_pass.equals("")) {
            DialogHandler.customDialog(SignupActivity.this, "Reset Password", "Please confirm password");
            return false;

        } else if (!password.matches(con_pass)) {
            DialogHandler.customDialog(SignupActivity.this, "Reset Password", "Password doesn't matches");
            return false;

        } else {
            return true;
        }

    }

    public void tabAndViewPager() {



      /*  //method that contains click listener implementation
        clickListeners();
*/
        //this is used to store memory of tabs that aren't active on screen
        //store memory means if user swipes from one tab to another it will store the filled state of fields
        //limit 5 means 5 tabs next to the current one
        viewPager.setOffscreenPageLimit(5);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // adding fragments into viewpager
        viewPagerAdapter.add(new FragmentSignUpPersonal(), "Personal");
        viewPagerAdapter.add(new FragmentSignUpSecurity(), "Security");
       /* viewPagerAdapter.add(new VaccinationDetailFragment(), "Vaccination Details");
        viewPagerAdapter.add(new NextOfKinFragment(), "Next of Kin");
        viewPagerAdapter.add(new RegistrationFeeFragment(), "Registration fee");*/

        //setting adapter to viewpager
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        tabLayout.setupWithViewPager(viewPager);
    }
}