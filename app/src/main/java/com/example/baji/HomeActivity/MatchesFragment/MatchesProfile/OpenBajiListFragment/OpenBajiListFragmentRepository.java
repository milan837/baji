package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model.OpenBajiResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenBajiListFragmentRepository {
    Context context;
    OpenBajiListFragmentContract.Presenter presenter;

    public OpenBajiListFragmentRepository(Context context, OpenBajiListFragmentContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitApi(ProgressDialog dialog, JsonObject jsonObject){
        Call<OpenBajiResponsePojo> apiInterface= ApiInstance.getInstance().getOpenBajiListResponse(Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<OpenBajiResponsePojo>() {
            @Override
            public void onResponse(Call<OpenBajiResponsePojo> call, Response<OpenBajiResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    dialog.cancel();
                    dialog.dismiss();
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_log_open_baji",response.message());
                }
            }

            @Override
            public void onFailure(Call<OpenBajiResponsePojo> call, Throwable t) {
                dialog.cancel();
                dialog.dismiss();
                Toast.makeText(context,"api failure",Toast.LENGTH_LONG).show();
                Log.i("milan_log_open_baji",t.getMessage());
            }
        });
    }
}
