package com.example.seungyeonlee.hardcarry;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seungyeonlee.hardcarry.Models.Match;
import com.example.seungyeonlee.hardcarry.Models.MatchList;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Response;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder>{
    Context context;
    List<String> items = new ArrayList<>();
    List<String> mlane = new ArrayList<>();
    List<Integer> mchampion = new ArrayList<>();
    List<Timestamp> mtimestamp = new ArrayList<>();
    List<String> mrole = new ArrayList<>();
    List<MatchList> mmlist = new ArrayList<>();

    int item_layout;



    public MatchListAdapter(Context context, List<MatchList> mmlist, int item_layout) {
        this.context = context;
        this.mmlist = mmlist;
//        this.mlane = lanes;
//        this.mtimestamp = times;
//        for (int i = 0; i < matches.size(); i++) {
//            this.mlane.add(matches.get(i).getLane());
//            this.mchampion.add(matches.get(i).getChampion());
//            this.mtimestamp.add(matches.get(i).getTimestamp());
//            this.mrole.add(matches.get(i).getRole());
//        }
        this.item_layout = item_layout;
    }

    @Override
    public MatchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list_item, null);
        return new MatchListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MatchListAdapter.ViewHolder holder, int position) {

//        holder.championImageView.setImageDrawable();
        System.out.println("라인있음?");
        System.out.println(mlane.get(position));

        holder.laneTextView.setText(mlane.get(position));
//
        Date date = new Date(String.valueOf(mtimestamp.get(position)));

        holder.timeTextView.setText((CharSequence) date);
//        holder.timeTextView.setText("시간간간");

    }

    @Override
    public int getItemCount() {
        return this.mlane.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView championImageView;
        TextView laneTextView, timeTextView;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            // ImageView
            championImageView = itemView.findViewById(R.id.champImageView);

            // TextView
            laneTextView = itemView.findViewById(R.id.laneTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
