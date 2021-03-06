package com.example.seungyeonlee.hardcarry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seungyeonlee.hardcarry.Models.League;
import com.example.seungyeonlee.hardcarry.Models.Match;
import com.example.seungyeonlee.hardcarry.Models.MatchList;
import com.example.seungyeonlee.hardcarry.Network.API;
import com.example.seungyeonlee.hardcarry.Network.Key;
import com.example.seungyeonlee.hardcarry.Network.RiotAPI;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayResultActivity extends AppCompatActivity {
    private ImageView profileImg, unrankImageView;
    private TextView summonerName,summonerLevelTextView, unrankTextView;
    private Integer summonerLevel;

    private String summonerAccountId, encryptedSummonerId, encryptedAccountId;
    private String name;
    private int profileIconId;

    private Bitmap bitmap;

    private RecyclerView recyclerView, matchRecyclerView;

    List<Integer> leaguePoint = new ArrayList<>();
    List<Integer> win = new ArrayList<>();
    List<Integer> lose = new ArrayList<>();
    List<String> queueType = new ArrayList<>();
    List<String> tier = new ArrayList<>();
    List<String> rank = new ArrayList<>();

    List<League> leg = new ArrayList<>();
    List<String> laneList = new ArrayList<>();
    List<Timestamp> timeList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        // ImageView
        profileImg = (ImageView) findViewById(R.id.imageView);
        GradientDrawable drawable=
                (GradientDrawable) getApplicationContext().getDrawable(R.drawable.round_img);
        profileImg.setBackground(drawable);
        profileImg.setClipToOutline(true);

        unrankImageView = (ImageView) findViewById(R.id.imageView2);


//        // TextView
        summonerName = (TextView) findViewById(R.id.textView);
        summonerLevelTextView = (TextView) findViewById(R.id.summonerLevelTextView);
        unrankTextView = (TextView) findViewById(R.id.textView7);

        // recyclerview 1
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

//        // matchRecyclerView
//        matchRecyclerView = (RecyclerView) findViewById(R.id.matchRecyclerView);
//        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
//
//        matchRecyclerView.setHasFixedSize(true);
//        matchRecyclerView.setLayoutManager(layoutManager2);


        // intent로 받아오기
        Intent intent = getIntent();

        summonerAccountId = intent.getStringExtra("summonerAccountId");
        encryptedAccountId = intent.getStringExtra("summonerAccountId");
        encryptedSummonerId = intent.getStringExtra("encryptedSummonerId");
        name = intent.getStringExtra("name");
        profileIconId = intent.getIntExtra("profileIconId", 1);
        summonerLevel = intent.getIntExtra("summonerLevel",1);
        System.out.println(summonerAccountId);
        System.out.println(encryptedSummonerId);
        System.out.println(name);
        System.out.println(profileIconId);
        System.out.println(summonerLevel);

        // 소환사 이름
        summonerName.setText(name);
        summonerLevelTextView.setText(Integer.toString(summonerLevel));
        setProfileImg();



        // retrofit 객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RiotAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<League>> call = api.getLeagueInfo(
                Key.getApiKey(), encryptedSummonerId
        );

        call.enqueue(new Callback<List<League>>() {

            @Override
            public void onResponse(Call<List<League>> call, Response<List<League>> response) {
                if (!response.body().isEmpty()) {
                    System.out.println("여기다!!");

                    leg = response.body();
                    System.out.println(leg.size());

                    for (int i=0;i < leg.size();i++){
                        System.out.println(leg.get(i).getQueueType());
                        leaguePoint.add(leg.get(i).getLeaguePoints());
                        win.add(leg.get(i).getWins());
                        lose.add(leg.get(i).getLosses());
                        queueType.add(leg.get(i).getQueueType());
                        tier.add(leg.get(i).getTier());
                        rank.add(leg.get(i).getRank());
                    }
                    recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), leaguePoint, win, lose, queueType, tier, rank,R.layout.activity_display_result));


                } else {
                    unrankImageView.setImageResource(R.drawable.provisional);
                    unrankTextView.setText("아직 랭킹이 없어요!!");
                    unrankImageView.setVisibility(View.VISIBLE);
                    unrankTextView.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "랭킹이 없네요:(", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<League>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


//        Call<MatchList> call2 = api.getMatchList(
//                Key.getApiKey(), encryptedAccountId
//        );
//
//        call2.enqueue(new Callback<MatchList>() {
//
//            @Override
//            public void onResponse(Call<MatchList> call, Response<MatchList> response) {
//                MatchList mm = response.body();
//                matchRecyclerView.setAdapter(new MatchListAdapter(getApplicationContext(),mm, R.layout.activity_display_result));
//
////                matchRecyclerView.setAdapter(new MatchListAdapter(getApplicationContext(),mm, R.layout.activity_display_result));
////
////
////                GeoEvent geoEvent1 = response.body();
////                //              Log.e("keshav","Location -> " +geoEvent1.responseMessage);
////
////
////
////                List<GeoEvent.GeoEvents> geoEventsArrayList = new ArrayList<GeoEvent.GeoEvents>();
////                geoEventsArrayList.addAll(geoEventsList);
////                for (GeoEvent.GeoEvents geoEvents : geoEventsList) {
////
////                    Log.e("keshav", "Location -> " + geoEvents.Location);
////                    Log.e("keshav", "DateTime -> " + geoEvents.DateTime);
////                }
////
////                if (geoEventsArrayList != null) {
////                    adapter.clear();
////                    adapter.addAll(geoEventsArrayList);
////                    adapter.notifyDataSetChanged();
////                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MatchList> call, Throwable t) {
//                System.out.println("여기 에러예요");
//                System.out.println(t.getMessage());
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    // 소환사 프로필 사진 설정
    private void setProfileImg(){

        Thread mThread = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    URL url = new URL("http://opgg-static.akamaized.net/images/profile_icons/profileIcon"+profileIconId+".jpg");
//                    URL url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/"+profileIconId+".png");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();

                    bitmap = BitmapFactory.decodeStream(is);

                    System.out.println("@@@@@@@herererere");
                    System.out.println(bitmap);


                } catch (MalformedURLException e) {
                    System.out.println("에러1");
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    System.out.println("파일이없어");
                } catch (IOException e) {
                    System.out.println("dpfj");
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        try {
            mThread.join();
            profileImg.setImageBitmap(bitmap);

        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
    }

}
