package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.CreateNewBaij;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Match;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.PaymentMethodBottomFragment;
import com.example.baji.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateNewBajiBottomFragment extends BottomSheetDialogFragment {

    @BindView(R.id.next_button)
    RelativeLayout nextButton;

    @BindView(R.id.amount_spinner)
    Spinner spinner;

    @BindView(R.id.team_one_radio_btn)
    RadioButton teamOneBtn;

    @BindView(R.id.team_two_radio_btn)
    RadioButton teamTwoBtn;

    @BindView(R.id.teamRadioGroup)
    RadioGroup radioGroup;



    String[] amount={"10","20","30","40","50","60","70","80","90","100"};
    ArrayAdapter<String> amountSpinnerAdapter;
    String selectedAmount="";

    Match match;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet_create_new_baji_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        match=(Match)getArguments().getSerializable("matches");
        initViews(view);
    }

    private void initViews(View view){
        amountSpinnerAdapter=new ArrayAdapter<>(getActivity(),R.layout.adapter_spinner,amount);
        spinner.setAdapter(amountSpinnerAdapter);

        teamOneBtn.setText(match.getTeamOne().getName());
        teamTwoBtn.setText(match.getTeamTwo().getName());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAmount=amount[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTeamName="";
                RadioButton radioButton=view.findViewById(radioGroup.getCheckedRadioButtonId());
                selectedTeamName=radioButton.getText().toString();


                int selectedTeamId=0;

                if(selectedTeamName.toLowerCase().trim().equals(match.getTeamOne().getName().toLowerCase().trim())){
                    selectedTeamId=match.getTeamOne().getId();
                }else if(selectedTeamName.toLowerCase().trim().equals(match.getTeamTwo().getName().toLowerCase().trim())){
                    selectedTeamId=match.getTeamTwo().getId();
                }

                if(selectedTeamName.isEmpty()){
                    Toast.makeText(getActivity(),"please select one team",Toast.LENGTH_LONG).show();
                }else if(selectedAmount.isEmpty()){
                    Toast.makeText(getActivity(),"please select the amount",Toast.LENGTH_LONG).show();
                }else{
                    Bundle bundle=new Bundle();
                    bundle.putString("matchId",String.valueOf(match.getId()));
                    bundle.putString("amount",selectedAmount);
                    bundle.putString("teamId", String.valueOf(selectedTeamId));
                    bundle.putString("type","create");
                    //create new baji further process
                    Log.i("molan_log_a",match.getId()+"=>"+
                            selectedAmount+"=>"+
                            String.valueOf(selectedTeamId));

                    PaymentMethodBottomFragment paymentMethodBottomFragment=PaymentMethodBottomFragment.getInstance();
                    paymentMethodBottomFragment.setArguments(bundle);
                    paymentMethodBottomFragment.show(getChildFragmentManager(),"paymentBottomFragment");
                }

            }
        });


    }

    public static CreateNewBajiBottomFragment getInstance(){
        return new CreateNewBajiBottomFragment();
    }


}