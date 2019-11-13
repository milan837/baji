package com.example.baji.HomeActivity.ProfileFragment;

import android.content.Context;

import com.example.baji.HomeActivity.ProfileFragment.Model.WithDrawAmountPojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.gson.JsonObject;
import com.paytm.pgsdk.easypay.utils.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    Context context;
    ProfilePresenterListener view;

    public ProfilePresenter(Context context, ProfilePresenterListener view) {
        this.context = context;
        this.view = view;
    }

    public void hitWithDrawApi(JsonObject jsonObject){
        Call<WithDrawAmountPojo> apiInterface= ApiInstance.getInstance().getWithDrawBtnResponse(
                Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<WithDrawAmountPojo>() {
            @Override
            public void onResponse(Call<WithDrawAmountPojo> call, Response<WithDrawAmountPojo> response) {
                if(response.code() == 200){
                    view.onWithDrawBtnSucess(response.body());
                }else{
                    view.onWithDrawBtnFailure();
                    Log.i("milan_withdraw_btn","on error code"+response.message());
                }
            }

            @Override
            public void onFailure(Call<WithDrawAmountPojo> call, Throwable t) {
                view.onWithDrawBtnFailure();
                Log.i("milan_withdraw_btn","on failure"+t.getMessage());
            }
        });
    }

    public interface ProfilePresenterListener{
        void onWithDrawBtnSucess(WithDrawAmountPojo withDrawAmountPojo);
        void onWithDrawBtnFailure();
    }
}
