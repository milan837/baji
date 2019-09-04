package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.GameWithMatchListResponsePojo;
import com.example.baji.Retrofit.ApiInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesListFragmentRepository {
    Context context;
    MatchesListFragmentContract.Presenter presenter;

    public MatchesListFragmentRepository(Context context, MatchesListFragmentContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitApi(){
        Call<GameWithMatchListResponsePojo> apiInterface= ApiInstance.getInstance().getGameAndMatchesList();
        apiInterface.enqueue(new Callback<GameWithMatchListResponsePojo>() {
            @Override
            public void onResponse(Call<GameWithMatchListResponsePojo> call, Response<GameWithMatchListResponsePojo> response) {
                if(response.code() == 200){
                    presenter.getDataFromApi(response.body());
                }else{
                    Toast.makeText(context,"error code",Toast.LENGTH_LONG).show();
                    Log.i("milan_log_home",response.message()+"error code");
                }
            }

            @Override
            public void onFailure(Call<GameWithMatchListResponsePojo> call, Throwable t) {
                Toast.makeText(context,"api failure",Toast.LENGTH_LONG).show();
                Log.i("milan_log_home",t.getMessage()+"api failure");
            }
        });
    }
}
