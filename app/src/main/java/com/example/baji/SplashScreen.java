package com.example.baji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.baji.BaseClasses.BaseActivity;
import com.example.baji.HomeActivity.HomeActivity;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.CreateNewBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.TransactionResponsePojo;
import com.example.baji.OnBoardingActivity.OnboardingActivity;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.gson.JsonObject;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                    SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
                    String userId=sharedPreferences.getString("userId",null);
                    if(userId == null){
                        Intent intent=new Intent(SplashScreen.this,OnboardingActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent=new Intent(SplashScreen.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

}
