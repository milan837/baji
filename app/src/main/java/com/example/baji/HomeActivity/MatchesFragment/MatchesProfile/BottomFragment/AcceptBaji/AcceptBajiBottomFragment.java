package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.AcceptBaji;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Match;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.PaymentMethodBottomFragment;
import com.example.baji.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcceptBajiBottomFragment extends BottomSheetDialogFragment {

    @BindView(R.id.next_button)
    RelativeLayout nextButton;

    @BindView(R.id.offered_username)
    TextView offerUsernameTxt;

    @BindView(R.id.offered_amount)
    TextView offerAmountTxt;

    @BindView(R.id.offered_team)
    TextView offerTeamTxt;

    @BindView(R.id.selected_team)
    TextView selectedTeamTxt;

    @BindView(R.id.selected_amount)
    TextView selectedAmountTxt;


    Match match;
    String bajiOfferByUsername,bajiOfferByTeamName;
    int openBajiId=0;
    double bajiOfferByAmount=0;

    String selectedTeam;
    int selectedTeamId=0;


    public static AcceptBajiBottomFragment getInstance(){
        return new AcceptBajiBottomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_accept_baji_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        match=(Match)getArguments().getSerializable("match");
        bajiOfferByAmount=getArguments().getInt("amount");
        openBajiId=getArguments().getInt("openBajiId");
        bajiOfferByTeamName=getArguments().getString("teamName");
        bajiOfferByUsername=getArguments().getString("username");

        initViews();
    }

    private void initViews(){
        offerAmountTxt.setText(String.valueOf(bajiOfferByAmount));
        offerTeamTxt.setText(bajiOfferByTeamName);
        offerUsernameTxt.setText("You are accepting the bhaji challenge of "+bajiOfferByUsername);

        seletedTeamProcess();

        selectedAmountTxt.setText(String.valueOf(bajiOfferByAmount));
        selectedTeamTxt.setText(selectedTeam);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(openBajiId == 0){
                    Toast.makeText(getActivity(),"baji id is empty",Toast.LENGTH_LONG).show();
                }else if(bajiOfferByAmount == 0){
                    Toast.makeText(getActivity(),"amount is empty",Toast.LENGTH_LONG).show();
                }else{
                    Bundle bundle=new Bundle();
                    bundle.putString("amount", String.valueOf(bajiOfferByAmount));
                    bundle.putString("openBajiId", String.valueOf(openBajiId));
                    bundle.putString("matchId", String.valueOf(match.getId()));
                    bundle.putString("type","accept");

                    Toast.makeText(getActivity(),String.valueOf(bajiOfferByAmount),Toast.LENGTH_LONG).show();

                    PaymentMethodBottomFragment paymentMethodBottomFragment=PaymentMethodBottomFragment.getInstance();
                    paymentMethodBottomFragment.setArguments(bundle);
                    paymentMethodBottomFragment.show(getChildFragmentManager(),"paymentBottomFragment");
                }

            }
        });
    }

    private void seletedTeamProcess(){
        if(bajiOfferByTeamName.toLowerCase().trim().equals(match.getTeamOne().getName().toLowerCase().trim())){
            selectedTeam=match.getTeamTwo().getName();
            selectedTeamId=match.getTeamTwo().getId();
        }
        if(bajiOfferByTeamName.toLowerCase().trim().equals(match.getTeamTwo().getName().toLowerCase().trim())){
            selectedTeam=match.getTeamOne().getName();
            selectedTeamId=match.getTeamOne().getId();
        }

        Log.i("milan_log",selectedTeam+"=>"+selectedTeamId+"\n"+
                match.getTeamOne().getName()+"=>"+match.getTeamOne().getId()+"\n"+
                match.getTeamTwo().getName()+"=>"+match.getTeamTwo().getId());
    }
}
