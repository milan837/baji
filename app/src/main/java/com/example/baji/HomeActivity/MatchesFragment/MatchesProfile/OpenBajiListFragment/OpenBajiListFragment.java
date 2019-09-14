package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Match;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model.OpenBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model.OpenBid;
import com.example.baji.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenBajiListFragment extends BaseFragment implements OpenBajiListFragmentContract.View {

    @BindView(R.id.open_baji_recycler_view)
    RecyclerView recyclerView;

    OpenBajiRecyclerViewAdapter adapter;
    List<OpenBid> openBidList=new ArrayList<>();
    OpenBajiListFragmentPresenter presenter;
    Match match;
    String userId="132";

    int matchesId=2,page=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_profile_open_baji_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        matchesId=getArguments().getInt("matchesId");
        match=(Match)getArguments().getSerializable("match");

        ButterKnife.bind(this,view);
        presenter=new OpenBajiListFragmentPresenter(getActivity(),this);
        initViews();
    }

    private void initViews(){
        callApi(matchesId,page);

        adapter=new OpenBajiRecyclerViewAdapter(getActivity(),openBidList,match,userId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    public void callApi(int matchesId,int page){
        showProgress();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("matchesId",matchesId);
        jsonObject.addProperty("page",page);
        presenter.sendDataToApi(dialog,jsonObject);
    }

    @Override
    public void displayResponse(OpenBajiResponsePojo openBajiResponsePojo) {
        dismissProgress();
            openBidList.addAll(openBajiResponsePojo.getOpenBids());
            adapter.notifyDataSetChanged();
    }
}
