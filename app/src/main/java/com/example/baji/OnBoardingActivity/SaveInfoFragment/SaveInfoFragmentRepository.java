package com.example.baji.OnBoardingActivity.SaveInfoFragment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.baji.OnBoardingActivity.SaveInfoFragment.Model.SaveUserInfoPojo;
import com.example.baji.Retrofit.ApiInstance;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveInfoFragmentRepository {
    Context context;
    SaveInfoFragmentContract.Presenter presenter;

    public SaveInfoFragmentRepository(Context context, SaveInfoFragmentContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitSaveInfoApi(String authKey,JsonObject jsonObject){
        Call<SaveUserInfoPojo> apiInterface=ApiInstance.getInstance().saveUserInfo(authKey,jsonObject);
        apiInterface.enqueue(new Callback<SaveUserInfoPojo>() {
            @Override
            public void onResponse(Call<SaveUserInfoPojo> call, Response<SaveUserInfoPojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else {
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();;
                    Log.i("milan_error_log_info", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<SaveUserInfoPojo> call, Throwable t) {
                Toast.makeText(context,"api failure",Toast.LENGTH_LONG).show();;
                Log.i("milan_error_log_faield", String.valueOf(t.getMessage()));
            }
        });
    }
}
