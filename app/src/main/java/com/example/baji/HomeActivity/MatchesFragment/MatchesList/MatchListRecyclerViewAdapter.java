package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Match;
import com.example.baji.R;
import com.example.baji.Utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchListRecyclerViewAdapter extends RecyclerView.Adapter<MatchListRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Match> list;
    String gameTitle;

    public MatchListRecyclerViewAdapter(Context context, List<Match> list,String gameTitle) {
        this.context = context;
        this.list = list;
        this.gameTitle=gameTitle;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_match_list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Match match=list.get(position);
        holder.teamOneName.setText(match.getTeamOne().getName());
        holder.teamTwoName.setText(match.getTeamTwo().getName());

        Glide.with(context).load(match.getTeamOne().getImageUrl()).into(holder.teamOneImage);
        Glide.with(context).load(match.getTeamTwo().getImageUrl()).into(holder.teamTwoImage);
        holder.matchesDate.setText(Utils.getDateFromTimeStamp(match.getTimeStamp()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("matches",match);
                bundle.putString("gameTitle",gameTitle);
                bundle.putString("totalMatches",String.valueOf(list.size()));
                Navigation.findNavController(v).navigate(R.id.matchListFragment_to_matchProfileFragment,bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.team_one_name)
        TextView teamOneName;

        @BindView(R.id.team_two_name)
        TextView teamTwoName;

        @BindView(R.id.matches_date)
        TextView matchesDate;

        @BindView(R.id.matches_time)
        TextView matchesTime;

        @BindView(R.id.team_one_image)
        ImageView teamOneImage;

        @BindView(R.id.team_two_image)
        ImageView teamTwoImage;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
