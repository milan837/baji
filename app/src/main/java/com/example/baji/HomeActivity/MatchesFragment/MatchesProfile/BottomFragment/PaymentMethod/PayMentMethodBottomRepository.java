package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.AcceptBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.CreateNewBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.TransactionResponsePojo;
import com.example.baji.Retrofit.ApiInstance;
import com.example.baji.Utils.Constant;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayMentMethodBottomRepository {
    Context context;
    PayMentMethodBottomContract.Presenter presenter;

    public PayMentMethodBottomRepository(Context context, PayMentMethodBottomContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitPaytmCheckSumApi(String amount,String orderId,String customerId){

        Call<PaytmChecksumResponsePojo> apiInterface= ApiInstance.getPhpApiInstance().getPaytmCheckSumApi(
                Constant.PAYTM_MERCHENT_ID,
                customerId,
                orderId ,
                Constant.PAYTM_INDUSTRY_ID,
                Constant.PAYTM_CHANNEL_ID,
                amount,
                Constant.PAYTM_WEBSITE,
                Constant.PAYTM_CALLBACK_URL
                );

        Log.i("milan_log",amount+" => "+orderId+" => "+customerId);

        apiInterface.enqueue(new Callback<PaytmChecksumResponsePojo>() {
            @Override
            public void onResponse(Call<PaytmChecksumResponsePojo> call, Response<PaytmChecksumResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.message()+" error code paytm checksum");
                }
            }

            @Override
            public void onFailure(Call<PaytmChecksumResponsePojo> call, Throwable t) {
                Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+" error code paytm checksum");
            }
        });

    }

    //api for create new baji
    public void hitCreateNewBajiApi(JsonObject jsonObject){
        Call<CreateNewBajiResponsePojo> apiINterface=ApiInstance.getInstance().getCreateNewBajiResponse(Constant.AUTH_KEY,jsonObject);
        apiINterface.enqueue(new Callback<CreateNewBajiResponsePojo>() {
            @Override
            public void onResponse(Call<CreateNewBajiResponsePojo> call, Response<CreateNewBajiResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromCreateBajiApi(response.body());
                }else{
                    Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.code()+" error code create new baji");
                    saveCreateBajiDataToPref(jsonObject);
                }
            }

            @Override
            public void onFailure(Call<CreateNewBajiResponsePojo> call, Throwable t) {
                Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+" failure create new baji");
                saveCreateBajiDataToPref(jsonObject);
            }
        });
    }

    //api for accept baji api
    public void hitAcceptBajiApi(JsonObject jsonObject){
        Call<AcceptBajiResponsePojo> apiInterface= ApiInstance.getInstance().getAcceptBajiResponse(Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<AcceptBajiResponsePojo>() {
            @Override
            public void onResponse(Call<AcceptBajiResponsePojo> call, Response<AcceptBajiResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromAcceptBajiApi(response.body());
                }else{
                    Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.code()+" error code Accept Baji");
                    saveAcceptBajiDataToPref(jsonObject);
                }
            }

            @Override
            public void onFailure(Call<AcceptBajiResponsePojo> call, Throwable t) {
                Toast.makeText(context,"",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+"  failure code Accept Baji");
                saveAcceptBajiDataToPref(jsonObject);
            }
        });
    }

    public void hitTransactionApi(JsonObject jsonObject){
        Call<TransactionResponsePojo> apiInterface=ApiInstance.getInstance().getSaveTransactionResponse(Constant.AUTH_KEY,jsonObject);
        apiInterface.enqueue(new Callback<TransactionResponsePojo>() {
            @Override
            public void onResponse(Call<TransactionResponsePojo> call, Response<TransactionResponsePojo> response) {
                if(response.code() ==200){
                    presenter.getDataFromTransactionApi(response.body());
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_log",response.code()+" error transaction Baji");
                    saveTransactionDataToPref(jsonObject);
                }
            }

            @Override
            public void onFailure(Call<TransactionResponsePojo> call, Throwable t) {
                Toast.makeText(context,"Api failure",Toast.LENGTH_LONG).show();
                Log.i("milan_log",t.getMessage()+"  failure transaction Baji");
                saveTransactionDataToPref(jsonObject);
            }
        });
    }

    public void saveTransactionDataToPref(JsonObject jsonObject){
        SharedPreferences sharedPreferences=context.getSharedPreferences("transaction",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("orderId", jsonObject.get("orderId").getAsString());
        editor.putString("userId", jsonObject.get("userId").getAsString());
        editor.putString("amount", jsonObject.get("amount").getAsString());
        editor.putString("timeStamp", jsonObject.get("timeStamp").getAsString());
        editor.commit();
        editor.apply();
    }

    public void saveCreateBajiDataToPref(JsonObject jsonObject){
        SharedPreferences sharedPreferences=context.getSharedPreferences("open",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("userId", jsonObject.get("userId").getAsString());
        editor.putString("matchesId", jsonObject.get("matchesId").getAsString());
        editor.putString("teamId", jsonObject.get("teamId").getAsString());
        editor.putString("amount", jsonObject.get("amount").getAsString());
        editor.commit();
        editor.apply();
    }

    public void saveAcceptBajiDataToPref(JsonObject jsonObject){
        SharedPreferences sharedPreferences=context.getSharedPreferences("onboard",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("openBajiId", jsonObject.get("openBajiId").getAsString());
        editor.putString("matchesId", jsonObject.get("matchesId").getAsString());
        editor.putString("userId", jsonObject.get("userId").getAsString());
        editor.commit();
        editor.apply();
    }
}
