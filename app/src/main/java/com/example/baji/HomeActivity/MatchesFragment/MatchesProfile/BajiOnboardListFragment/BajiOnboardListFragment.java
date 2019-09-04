package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model.BajiOnboardResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model.OnboardBaji;
import com.example.baji.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BajiOnboardListFragment extends BaseFragment implements BajiOnboardListFragmentContract.View {

    @BindView(R.id.baji_onboard_recycler_view)
    RecyclerView recyclerView;

    BajiOnboardRecyclerViewAdapter adapter;
    List<OnboardBaji> onboardBajiList=new ArrayList<>();
    BajiOnboardListFragmentPresenter presenter;

    int matchesId=2,page=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_profile_baji_onboard_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        presenter=new BajiOnboardListFragmentPresenter(getActivity(),this);
        initViews();
    }

    private void initViews(){
        callApi(matchesId,page);

        adapter=new BajiOnboardRecyclerViewAdapter(getActivity(),onboardBajiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void callApi(int matchesId,int page){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("matchesId",matchesId);
        jsonObject.addProperty("page",page);
        presenter.sendDataToApi(jsonObject);
    }
    @Override
    public void displayResponse(BajiOnboardResponsePojo bajiOnboardResponsePojo) {

        onboardBajiList.addAll(bajiOnboardResponsePojo.getOnboardBaji());
        adapter.notifyDataSetChanged();
    }
}
