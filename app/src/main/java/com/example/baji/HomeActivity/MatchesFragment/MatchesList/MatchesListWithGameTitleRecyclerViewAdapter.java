package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Game;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.GameWithMatchListResponsePojo;
import com.example.baji.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchesListWithGameTitleRecyclerViewAdapter extends RecyclerView.Adapter<MatchesListWithGameTitleRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Game> gameList;
    MatchListRecyclerViewAdapter adapter;

    public MatchesListWithGameTitleRecyclerViewAdapter(Context context,List<Game> gameList) {
        this.context = context;
        this.gameList=gameList;
    }

    @NonNull
    @Override
    public MatchesListWithGameTitleRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_match_list_with_game_title_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesListWithGameTitleRecyclerViewAdapter.MyViewHolder holder, int position) {
        Game game=gameList.get(position);

        holder.gameTitleName.setText(game.getName());
        holder.totalMatches.setText(String.valueOf(game.getMatches().size())+" Matches");



        adapter=new MatchListRecyclerViewAdapter(context,game.getMatches(),game.getName());
        holder.matchListRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.matchListRecyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(holder.matchListRecyclerView);
        holder.matchListRecyclerView.setNestedScrollingEnabled(false);
        holder.matchListRecyclerView.getAdapter().notifyDataSetChanged();

        holder.matchListRecyclerView.setFocusable(false);

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.matchListRecyclerView)
        RecyclerView matchListRecyclerView;

        @BindView(R.id.game_title_name)
        TextView gameTitleName;

        @BindView(R.id.total_matches_list)
        TextView totalMatches;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
