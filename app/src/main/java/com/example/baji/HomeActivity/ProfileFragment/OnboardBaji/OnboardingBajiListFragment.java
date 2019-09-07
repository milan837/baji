package com.example.baji.HomeActivity.ProfileFragment.OnboardBaji;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.ProfileFragment.OnboardBaji.Model.OnboardBaji;
import com.example.baji.HomeActivity.ProfileFragment.OnboardBaji.Model.ProfileOnboardingBajiListResponsePojo;
import com.example.baji.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardingBajiListFragment extends BaseFragment implements OnboardingBajiListContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    OnboardingBajiListRecyclerViewAdapter adapter;
    List<OnboardBaji> list=new ArrayList<>();
    OnboardingBajiListPresenter presenter;

    String userId;
    int page=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_onboard_baji_list_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        userId=getArguments().getString("userId");
        presenter=new OnboardingBajiListPresenter(getActivity(),this);
        initViews();
    }

    public void initViews(){
        callApi();
        adapter=new OnboardingBajiListRecyclerViewAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void callApi(){
        showProgress();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("userId",userId);
        jsonObject.addProperty("page",page);
        presenter.sendDataFromApi(jsonObject);
    }

    @Override
    public void displayResponse(ProfileOnboardingBajiListResponsePojo profileOnboardingBajiListResponsePojo) {
        dismissProgress();
        list.addAll(profileOnboardingBajiListResponsePojo.getOnboardBaji());
        adapter.notifyDataSetChanged();
    }
}
