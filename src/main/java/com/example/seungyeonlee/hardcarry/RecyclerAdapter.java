package com.example.seungyeonlee.hardcarry;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<String> items;
    private List<Integer> mLeaguePoint;
    private List<Integer> mWin;
    private List<Integer> mLose;
    private List<String> mQueueType;
    private List<String> mTier;
    private List<String> mRank;
    int item_layout;

    public RecyclerAdapter(Context context, List<Integer> leaguePoint, List<Integer> win, List<Integer> lose, List<String> queueType, List<String> tier, List<String> rank,int item_layout) {
        this.context = context;

        this.mQueueType = queueType;
        this.mTier = tier;
        this.mLeaguePoint = leaguePoint;
        this.mWin = win;
        this.mLose = lose;
        this.mRank = rank;

        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opgg_cardview, null);
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String queueType = mQueueType.get(position);
//        String tier = mTier.get(position);
//        String rank = mRank.get(position);
//        Integer leaguePoint = mLeaguePoint.get(position);
//        Integer win = mWin.get(position);
//        Integer lose = mLose.get(position);
//
//        holder.queueTypeTextView.setText(queueType);
//        holder.tierTextView.setText(tier.concat("\t"+ rank));
//        holder.leaguePointTextView.setText(String.valueOf(leaguePoint).concat("LP"));
//        holder.winTextView.setText(String.valueOf(win).concat("승"));
//        holder.loseTextView.setText(String.valueOf(lose).concat("패"));

        String queueType = mQueueType.get(position);
        String tier = mTier.get(position);
        String rank = mRank.get(position);
        Integer leaguePoint = mLeaguePoint.get(position);
        Integer win = mWin.get(position);
        Integer lose = mLose.get(position);

        holder.queueTypeTextView.setText(queueType);
        if (tier.equals("CHALLENGER") || tier.equals("MASTER")) {
            holder.tierTextView.setText(tier);
        } else {
            holder.tierTextView.setText(tier.concat("\t"+ rank));
        }
        System.out.println("티어 확인하자");
        System.out.println(tier);
        System.out.println(rank);

        holder.leaguePointTextView.setText(String.valueOf(leaguePoint).concat("LP"));
//        holder.winTextView.setText(String.valueOf(win).concat("승"));
//        holder.loseTextView.setText(String.valueOf(lose).concat("패"));

        double rate =
                (float) ((double) win
                        / (double) (lose + win) *100.0);
//        if (rate > 50) {
////            holder.winRateTextView.setTextColor();
//            holder.winRateTextView.setTextColor(Color.RED);
//        } else {
//            holder.winRateTextView.setTextColor(Color.BLUE);
//        }
        holder.winRateTextView.setText(String.valueOf(win).concat("승/")+String.valueOf(lose).concat("패"+"\t("+Math.round(rate)+"%)"));

        String imgName;
        switch (tier) {
            case "CHALLENGER":
                imgName = tier.toLowerCase();
                break;

            case "MASTER":
                imgName = tier.toLowerCase();
                break;

            case "PLATINUM":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;

                }
                break;

            case "DIAMOND":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
                break;

            case "GOLD":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
                break;

            case "SLIVER":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
                break;

            case "BRONZE":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
                break;
            default:
                imgName = "provisional";
                break;
        }

        int id = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        holder.tierImageView.setImageResource(id);

        if (tier.isEmpty()) {
//            int id2 = context.getResources().getIdentifier("provisional", "drawable", context.getPackageName());
//            holder.tierImageView.setImageResource(id2);
        }

    }

    @Override
    public int getItemCount() {
        return this.mQueueType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView tierImageView;
        TextView queueTypeTextView, tierTextView, leaguePointTextView, winTextView, loseTextView, winRateTextView;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            // ImageView
//            tierImageView = itemView.findViewById(R.id.imageView);
            tierImageView = itemView.findViewById(R.id.imageView3);

            // TextView
//            queueTypeTextView = itemView.findViewById(R.id.textView);
//            tierTextView = itemView.findViewById(R.id.textView2);
//            leaguePointTextView = itemView.findViewById(R.id.textView3);
//            winTextView = itemView.findViewById(R.id.textView4);
//            loseTextView = itemView.findViewById(R.id.textView5);
//            winRateTextView = itemView.findViewById(R.id.textView6);
//            cardview = (CardView) itemView.findViewById(R.id.cardview);

            queueTypeTextView = itemView.findViewById(R.id.textView9);
            tierTextView = itemView.findViewById(R.id.textView10);
            leaguePointTextView = itemView.findViewById(R.id.textView11);
//            winTextView = itemView.findViewById(R.id.textView);
//            loseTextView = itemView.findViewById(R.id.textView5);
            winRateTextView = itemView.findViewById(R.id.rateTextView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }

}