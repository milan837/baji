package com.example.baji.HomeActivity.ProfileFragment.OpenBaji;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.ProfileFragment.OpenBaji.Model.ProfileOpenBajiListResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenBajiListRepository {
    Context context;
    OpenBajiListContract.Presenter presenter;

    public OpenBajiListRepository(Context context, OpenBajiListContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitApi(JsonObject jsonObject){
        Call<ProfileOpenBajiListResponsePojo> apiInterface= ApiInstance.getInstance().getProfileOpenBajiList(Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<ProfileOpenBajiListResponsePojo>() {
            @Override
            public void onResponse(Call<ProfileOpenBajiListResponsePojo> call, Response<ProfileOpenBajiListResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.message()+"error code profile open baji");
                }
            }

            @Override
            public void onFailure(Call<ProfileOpenBajiListResponsePojo> call, Throwable t) {
                Toast.makeText(context,"api failure",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+"api failure profile open baji");
            }
        });
    }
}
