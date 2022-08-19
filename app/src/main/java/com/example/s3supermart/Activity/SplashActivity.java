package com.example.s3supermart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.s3supermart.Helper.PrefsManager;
import com.example.s3supermart.R;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    Handler handler;
    Animation animationBounce, animationMove_L_to_C, animationMove_C_to_R, animationShake;
    PrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        prefsManager = new PrefsManager(SplashActivity.this);

        //to animate splash screen
        splashAnimation();

    }

    //method that contains implementation of animated splash screen
    public void splashAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView = findViewById(R.id.image);
                animationBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce_animation);
                animationShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                animationMove_L_to_C = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_animation_left_to_center);
                animationMove_C_to_R = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_animation_center_to_right);
                imageView.startAnimation(animationMove_L_to_C);
                imageView.setVisibility(View.VISIBLE);

                // Your fade animation
                AlphaAnimation fadeOutAnimation = new AlphaAnimation(1, 0);
                fadeOutAnimation.setDuration(300);

                //Create animation set and add both animations to run simultaneously.
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(animationMove_C_to_R);

                //Now set animation  listener on your rotate animation
                animationMove_L_to_C.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imageView.startAnimation(animationShake);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                animationShake.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imageView.startAnimation(animationMove_C_to_R);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                animationMove_C_to_R.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //to check if user was previously logged in or not
                        getloginPref();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        }, 5);


    }

    //to check if user was previously logged in, in order to redirect him to main screen
    public void getloginPref() {

        if (prefsManager.getLogin()) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));

        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
    }

}