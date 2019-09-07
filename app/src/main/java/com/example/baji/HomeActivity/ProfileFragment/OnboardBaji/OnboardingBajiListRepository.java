package com.example.baji.HomeActivity.ProfileFragment.OnboardBaji;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.ProfileFragment.OnboardBaji.Model.ProfileOnboardingBajiListResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnboardingBajiListRepository {
    Context context;
    OnboardingBajiListContract.Presenter presenter;

    public OnboardingBajiListRepository(Context context, OnboardingBajiListContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitApi(JsonObject jsonObject){
        Call<ProfileOnboardingBajiListResponsePojo> apiInterface= ApiInstance.getInstance().getProfileOnboardBajiList(
                Constant.AUTH_KEY,
                jsonObject
        );

        apiInterface.enqueue(new Callback<ProfileOnboardingBajiListResponsePojo>() {
            @Override
            public void onResponse(Call<ProfileOnboardingBajiListResponsePojo> call, Response<ProfileOnboardingBajiListResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.message()+" error code profile onboarding baji");
                }
            }

            @Override
            public void onFailure(Call<ProfileOnboardingBajiListResponsePojo> call, Throwable t) {
                Toast.makeText(context,"api failure",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+" api failure profile onboarding baji");
            }
        });
    }
}
