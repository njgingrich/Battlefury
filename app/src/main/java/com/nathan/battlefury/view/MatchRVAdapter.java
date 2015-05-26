package com.nathan.battlefury.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nathan.battlefury.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nathan on 5/26/15.
 */
public class MatchRVAdapter extends RecyclerView.Adapter<MatchRVAdapter.MatchViewHolder> {

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView match_id;

        public MatchViewHolder(View v) {
            super(v);
            cv       = (CardView)v.findViewById(R.id.match_card);
            match_id = (TextView)v.findViewById(R.id.match_id);
        }
    }

    List<Long> match_ids;

    public MatchRVAdapter() {
        match_ids = new LinkedList<>();
    }

    public MatchRVAdapter(List<Long> ids) {
        this.match_ids = ids;
    }

    public void addAll(List<Long> ids) {
        match_ids = ids;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        MatchViewHolder mvh = new MatchViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        holder.match_id.setText(match_ids.get(position) + "");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return match_ids.size();
    }

}
