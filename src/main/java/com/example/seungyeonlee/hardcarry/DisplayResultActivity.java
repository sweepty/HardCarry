package com.example.seungyeonlee.hardcarry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seungyeonlee.hardcarry.Models.League;
import com.example.seungyeonlee.hardcarry.Network.API;
import com.example.seungyeonlee.hardcarry.Network.Key;
import com.example.seungyeonlee.hardcarry.Network.RiotAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayResultActivity extends AppCompatActivity {
    private ImageView profileImg, tierImageView;
    private TextView summonerName, tierTextView, leaguePointTextView, winTextView, loseTextView, winRateTextView;
//    private ListView listView;

    private String summonerAccountId, encryptedSummonerId;
    private String name;
    private Integer profileIconId;

    private Bitmap bitmap;

    private List matchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        // ImageView
        profileImg = (ImageView) findViewById(R.id.imageView);
        tierImageView = (ImageView) findViewById(R.id.imageView3);

        // TextView
        summonerName = (TextView) findViewById(R.id.textView);
        tierTextView = (TextView) findViewById(R.id.tierTextView);
        leaguePointTextView = (TextView) findViewById(R.id.leaguePointTextView);
        winTextView = (TextView) findViewById(R.id.winTextView);
        loseTextView = (TextView) findViewById(R.id.loseTextView);
        winRateTextView = (TextView) findViewById(R.id.winRateTextView);


//        listView = (ListView) findViewById(R.id.listView);


        // intent로 받아오기
        Intent intent = getIntent();

        summonerAccountId = intent.getStringExtra("summonerAccountId");
        encryptedSummonerId = intent.getStringExtra("encryptedSummonerId");
        name = intent.getStringExtra("name");
        profileIconId = intent.getIntExtra("profileIconId", 1);

        System.out.println(summonerAccountId);
        System.out.println(encryptedSummonerId);
        System.out.println(name);
        System.out.println(profileIconId);

        // 소환사 이름
        summonerName.setText(name);
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
//                    System.out.println(response.body().get(0).getWins());

                    tierTextView.setText(response.body().get(0).getTier()+"\t"+response.body().get(0).getRank());
                    leaguePointTextView.setText(response.body().get(0).getLeaguePoints().toString().concat("LP"));
                    winTextView.setText(response.body().get(0).getWins().toString().concat("승"));
                    loseTextView.setText(response.body().get(0).getLosses().toString().concat("패"));
//                    System.out.println( (double) x / (double) y * 100.0 + "%");
                    double rate =
                            (float) ((double) response.body().get(0).getWins()
                                                        / (double) (response.body().get(0).getLosses() + response.body().get(0).getWins()) *100.0);
                    winRateTextView.setText("승률 \t"+Math.round(rate)+"%");

                    setTierImg(response.body().get(0).getTier(), response.body().get(0).getRank());

                } else {
                    tierTextView.setText("무한한 가능성이 있는 언랭이에요:)");
                    Toast.makeText(getApplicationContext(), "랭킹이 없네요:(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<League>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



//        Call<List<MatchList>> call = api.getMatchList(
//                Key.getApiKey(), summonerAccountId
//        );
//
//        call.enqueue(new Callback<List<MatchList>>() {
//            @Override
//            public void onResponse(Call<List<MatchList>> call, Response<List<MatchList>> response) {
//                System.out.println("나오긴하냐");
//                System.out.println(response.body());
//
//
//                matchList = response.body();
//
//                for (Object aa: matchList
//                        ) {
//                    System.out.println(aa);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<MatchList>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void setProfileImg(){
        // 소환사 프로필 사진 설정

        Thread mThread = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    URL url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/"+profileIconId+".png");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    System.out.println("@@@@@@@herererere");
                    System.out.println(bitmap);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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
    private void setTierImg(String tier, String rank) {
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
            case "DIAMOND":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
            case "GOLD":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
            case "SLIVER":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
            case "BRONZE":
                switch (rank) {
                    case "I": imgName = tier.toLowerCase().concat("_i"); break;
                    case "II": imgName = tier.toLowerCase().concat("_ii"); break;
                    case "III": imgName = tier.toLowerCase().concat("_iii"); break;
                    case "IV": imgName = tier.toLowerCase().concat("_iv"); break;
                    default: imgName = tier.toLowerCase().concat("_v"); break;
                }
            default:
                imgName = "provisional";
                break;
        }
        imgName.concat(".png");
        Context context = tierImageView.getContext();
        int id = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        tierImageView.setImageResource(id);
//        return imgName;
    }
}
