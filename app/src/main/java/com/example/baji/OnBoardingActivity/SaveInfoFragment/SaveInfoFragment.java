package com.example.baji.OnBoardingActivity.SaveInfoFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveInfoFragment extends BaseFragment {

    @BindView(R.id.awater_recycler_view)
    RecyclerView awaterRecyclerView;

    @BindView(R.id.next_button)
    CardView nextButton;

    @BindView(R.id.back_button)
    RelativeLayout backButton;

    AwaterListRecyclerViewAdapter adapter;
    List<String> awaterImageList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onboarding_save_info_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        addAwaterToList();

        adapter=new AwaterListRecyclerViewAdapter(getActivity(),awaterImageList);
        LinearLayoutManager linearLayoutManager=new GridLayoutManager(getActivity(),4);
        awaterRecyclerView.setLayoutManager(linearLayoutManager);
        awaterRecyclerView.setItemAnimator(new DefaultItemAnimator());
        awaterRecyclerView.setAdapter(adapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getSelectedAwater() != null){
                    Log.i("milan_log",adapter.getSelectedAwater());
                }else{
                    showLongToast(getActivity(),"Please select your awater");
                }
            }
        });

    }

    private void addAwaterToList(){
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(1).png?alt=media&token=735b366b-a7b3-4564-86e6-eb9ee84dde08");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(2).png?alt=media&token=57226441-d68a-46d5-875d-df1dd8857ca3");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project.png?alt=media&token=4c844c5e-c277-4d0b-bdfa-f9d2f7ef42f3");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(8).png?alt=media&token=af86328b-378a-4e16-8911-453a26861330");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(7).png?alt=media&token=1e914f9b-96e4-4a5c-9872-6f6ae6145d45");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(6).png?alt=media&token=0c98c3db-de4f-4345-85d2-7c29fd421320");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(5).png?alt=media&token=ae6366b0-d042-49d4-a28d-87748756e562");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(4).png?alt=media&token=da92089d-7785-492f-aa84-3c4e4de8e533");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(3).png?alt=media&token=d5a341c8-9757-4f6a-8e3b-18b8d4926797");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(5).png?alt=media&token=ae6366b0-d042-49d4-a28d-87748756e562");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(4).png?alt=media&token=da92089d-7785-492f-aa84-3c4e4de8e533");
        awaterImageList.add("https://firebasestorage.googleapis.com/v0/b/internship-2badf.appspot.com/o/awater%2FNew%20Project%20(3).png?alt=media&token=d5a341c8-9757-4f6a-8e3b-18b8d4926797");
        //awaterRecyclerView.getAdapter().notifyDataSetChanged();
    }



}
