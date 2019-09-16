package com.example.baji.HomeActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.baji.BaseClasses.BaseActivity;
import com.example.baji.HomeActivity.ActiveBajiListFragment.ActiveBajiListFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.CreateNewBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.TransactionResponsePojo;
import com.example.baji.HomeActivity.ProfileFragment.ProfileFragment;
import com.example.baji.HomeActivity.SettingFragment.SettingFragment;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.baji.R;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    MatchesFragment fragment1 = new MatchesFragment();
    ActiveBajiListFragment fragment2 = new ActiveBajiListFragment();
    ProfileFragment fragment3 = new ProfileFragment();
    SettingFragment fragment4 = new SettingFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //loadFragment(new MatchesFragment());
        loadAllFragment();

        //loding fragment according to the nav bottom bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_matches:
                        fm.beginTransaction().hide(active).show(fragment1).commit();
                        active = fragment1;
                        return true;

                    case R.id.nav_baji:
                        fm.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                        return true;

                    case R.id.nav_profile:
                        fm.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                        return true;

                    case R.id.nav_setting:
                        fm.beginTransaction().hide(active).show(fragment4).commit();
                        active = fragment4;
                        return true;
                }

                return false;
            }
        });

        //checkin wheather the transaction,open,onboarding api are faield and store in share preferences
        //hitting the api until sucess and once sucess cleared all data from preferences
        checkOpenBajiData();
        checkOnboardBajiData();
        checkTransactionData();
    }

    public void loadAllFragment(){
        fm.beginTransaction().add(R.id.fragment_container, fragment4, "3").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();
    }

    private void checkOpenBajiData() {
        SharedPreferences sharedPreferences = getSharedPreferences("open", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userId = sharedPreferences.getString("userId", null);
        String matchesId = sharedPreferences.getString("matchesId", null);
        String teamId = sharedPreferences.getString("teamId", null);
        String amount = sharedPreferences.getString("amount", null);

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("userId",userId);
        jsonObject.addProperty("matchesId",matchesId);
        jsonObject.addProperty("teamId",teamId);
        jsonObject.addProperty("amount",amount);

        if (userId != null || matchesId != null || teamId != null || amount != null) {
            Call<CreateNewBajiResponsePojo> apiINterface = ApiInstance.getInstance().getCreateNewBajiResponse(Constant.AUTH_KEY, jsonObject);
            apiINterface.enqueue(new Callback<CreateNewBajiResponsePojo>() {
                @Override
                public void onResponse(Call<CreateNewBajiResponsePojo> call, Response<CreateNewBajiResponsePojo> response) {
                    if (response.code() == 200) {
                        editor.clear();
                        editor.commit();
                    } else {
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                        Log.i("milan_log", response.code() + " error code create new baji");
                    }
                }

                @Override
                public void onFailure(Call<CreateNewBajiResponsePojo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.i("milan_log", t.getMessage() + " failure create new baji");
                }
            });

        }
    }

    private void checkOnboardBajiData() {
        SharedPreferences sharedPreferences = getSharedPreferences("onboard", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String openBajiId = sharedPreferences.getString("openBajiId", null);
        String userId = sharedPreferences.getString("userId", null);
        String matchesId = sharedPreferences.getString("matchesId", null);
        String amount = sharedPreferences.getString("amount", null);

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("openBajiId",openBajiId);
        jsonObject.addProperty("matchesId",matchesId);
        jsonObject.addProperty("userId",userId);

        if (openBajiId != null || userId != null || matchesId != null || amount != null) {
            Call<CreateNewBajiResponsePojo> apiINterface = ApiInstance.getInstance().getCreateNewBajiResponse(Constant.AUTH_KEY, jsonObject);
            apiINterface.enqueue(new Callback<CreateNewBajiResponsePojo>() {
                @Override
                public void onResponse(Call<CreateNewBajiResponsePojo> call, Response<CreateNewBajiResponsePojo> response) {
                    if (response.code() == 200) {
                        editor.clear();
                        editor.commit();
                    } else {
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                        Log.i("milan_log", response.code() + " error code create new baji");
                    }
                }

                @Override
                public void onFailure(Call<CreateNewBajiResponsePojo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.i("milan_log", t.getMessage() + " failure create new baji");
                }
            });

        }
    }

    private void checkTransactionData(){
        SharedPreferences sharedPreferences=getSharedPreferences("transaction",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String orderId=sharedPreferences.getString("orderId",null);
        String amount=sharedPreferences.getString("amount",null);
        String userId=sharedPreferences.getString("userId",null);
        String timeStamp=sharedPreferences.getString("timeStamp",null);

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("orderId",orderId);
        jsonObject.addProperty("userId",userId);
        jsonObject.addProperty("amount",amount);
        jsonObject.addProperty("timeStamp",timeStamp);

        if(orderId != null || amount != null || userId != null || timeStamp != null){
            Call<TransactionResponsePojo> apiInterface= ApiInstance.getInstance().getSaveTransactionResponse(Constant.AUTH_KEY,jsonObject);
            apiInterface.enqueue(new Callback<TransactionResponsePojo>() {
                @Override
                public void onResponse(Call<TransactionResponsePojo> call, Response<TransactionResponsePojo> response) {
                    if(response.code() ==200){
                        editor.clear();
                        editor.commit();
                    }else{
                        Toast.makeText(getApplicationContext(),"error code",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<TransactionResponsePojo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Api failure",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",t.getMessage()+"  failure transaction Baji");
                }
            });
        }

    }

}
