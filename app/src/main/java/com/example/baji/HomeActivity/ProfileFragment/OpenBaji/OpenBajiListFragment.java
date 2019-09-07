package com.example.baji.HomeActivity.ProfileFragment.OpenBaji;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.ProfileFragment.OpenBaji.Model.OpenBid;
import com.example.baji.HomeActivity.ProfileFragment.OpenBaji.Model.ProfileOpenBajiListResponsePojo;
import com.example.baji.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenBajiListFragment extends BaseFragment implements OpenBajiListContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    OpenBajiListRecyclerViewAdapter adapter;
    List<OpenBid> list=new ArrayList<>();

    OpenBajiListPresenter presenter;
    String userId="";
    int page=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_open_baji_list_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        userId=getArguments().getString("userId");
        presenter=new OpenBajiListPresenter(getActivity(),this);
        initViews();
    }

    public void initViews(){
        callApi();
        adapter=new OpenBajiListRecyclerViewAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void callApi(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("userId",userId);
        jsonObject.addProperty("page",page);
        presenter.sendDataToApi(jsonObject);
    }


    @Override
    public void displayResponse(ProfileOpenBajiListResponsePojo profileOpenBajiListResponsePojo) {
        list.addAll(profileOpenBajiListResponsePojo.getOpenBids());
        adapter.notifyDataSetChanged();
    }
}
