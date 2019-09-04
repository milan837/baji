package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baji.HomeActivity.HomeActivity;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.AcceptBaji.AcceptBajiBottomFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.MatchesProfileFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model.OpenBid;
import com.example.baji.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenBajiRecyclerViewAdapter extends RecyclerView.Adapter<OpenBajiRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<OpenBid> openBidsList;

    public OpenBajiRecyclerViewAdapter(Context context, List<OpenBid> openBidsList) {
        this.context = context;
        this.openBidsList=openBidsList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_open_baji_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OpenBid bid=openBidsList.get(position);

        holder.username.setText(bid.getUser().getUsername());
        holder.bajiAmount.setText(String.valueOf(bid.getAmount()));
        holder.teamName.setText(bid.getTeam().getName());

        Glide.with(context).load(bid.getUser().getImageUrl()).into(holder.profilePic);
        holder.acceptBajiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcceptBajiBottomFragment acceptBajiBottomFragment=AcceptBajiBottomFragment.getInstance();
                acceptBajiBottomFragment.show(((HomeActivity)context).getSupportFragmentManager(),"acceptBajiFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return openBidsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_image)
        ImageView profilePic;

        @BindView(R.id.username)
        TextView username;

        @BindView(R.id.team_name)
        TextView teamName;

        @BindView(R.id.baji_amount)
        TextView bajiAmount;

        @BindView(R.id.baji_now_button)
        Button acceptBajiButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
