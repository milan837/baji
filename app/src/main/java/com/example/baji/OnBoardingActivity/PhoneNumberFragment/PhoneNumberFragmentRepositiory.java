package com.example.baji.OnBoardingActivity.PhoneNumberFragment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.example.baji.Retrofit.ApiInstance;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberFragmentRepositiory {

    Context context;
    PhoneNumberFragmentContract.Presenter presenter;

    public PhoneNumberFragmentRepositiory(Context context, PhoneNumberFragmentContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void registerUserApi(JsonObject jsonObject){
        Call<UserRegisterPojo> apiInterface= ApiInstance.getInstance().registerUser(jsonObject);
        apiInterface.enqueue(new Callback<UserRegisterPojo>() {
            @Override
            public void onResponse(Call<UserRegisterPojo> call, Response<UserRegisterPojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_api",response.message());
                }
            }

            @Override
            public void onFailure(Call<UserRegisterPojo> call, Throwable t) {
                Toast.makeText(context,"api failed",Toast.LENGTH_LONG).show();
                Log.i("milan_api",t.getMessage());
            }
        });

    }
}
