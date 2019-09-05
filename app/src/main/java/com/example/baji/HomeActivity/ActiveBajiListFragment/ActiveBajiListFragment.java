package com.example.baji.HomeActivity.ActiveBajiListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.ActiveBajiListFragment.Model.ActiveBajiListResponsePojo;
import com.example.baji.HomeActivity.ActiveBajiListFragment.Model.OnboardBaji;
import com.example.baji.R;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveBajiListFragment extends BaseFragment implements ActiveBajiListContract.View {


    @BindView(R.id.activeBajiListRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.total_baji_list)
    TextView totalBaji;

    ActiveBajiListPresenter presenter;
    ActiveBajiListRecyclerViewAdapter adapter;
    List<OnboardBaji> list=new ArrayList<>();
    int page=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_nav_baji_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        presenter=new ActiveBajiListPresenter(getActivity(),this);
        initViews();
    }

    private void initViews(){
        callApi(page);

        adapter=new ActiveBajiListRecyclerViewAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void callApi(int page){
        showProgress();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("page",page);
        presenter.sendDataToApi(jsonObject);
    }

    @Override
    public void displayResponse(ActiveBajiListResponsePojo activeBajiListResponsePojo) {
        dismissProgress();
        list.addAll(activeBajiListResponsePojo.getOnboardBaji());
        adapter.notifyDataSetChanged();

        totalBaji.setText("Total "+list.size());
    }
}
