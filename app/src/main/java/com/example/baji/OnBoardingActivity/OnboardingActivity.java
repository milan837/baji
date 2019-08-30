package com.example.baji.OnBoardingActivity;

import android.os.Bundle;

import com.example.baji.BaseClasses.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.baji.R;
import com.google.firebase.FirebaseApp;

public class OnboardingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }
}
