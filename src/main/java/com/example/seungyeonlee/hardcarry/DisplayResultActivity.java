package com.example.seungyeonlee.hardcarry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seungyeonlee.hardcarry.Models.MatchList;
import com.example.seungyeonlee.hardcarry.Network.API;
import com.example.seungyeonlee.hardcarry.Network.Constant;
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
    private ImageView profileImg;
    private TextView summonerName;
    private String summonerAccountId;
    private String name;
    private Integer profileIconId;

    private Bitmap bitmap;

    private List matchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);


        profileImg = (ImageView) findViewById(R.id.imageView);
        summonerName = (TextView) findViewById(R.id.textView);

        // intent로 받아오기
        Intent intent = getIntent();
        summonerAccountId = intent.getStringExtra("summonerAccountId");
        name = intent.getStringExtra("name");
        profileIconId = intent.getIntExtra("profileIconId", 1);

        // 소환사 이름
        summonerName.setText(name);

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

//        // retrofit 객체 생성
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RiotAPI.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        API api = retrofit.create(API.class);
//
//        Call<List<MatchList>> m_call = api.getMatchList(
//                Key.getApiKey(), summonerAccountId
//        );
//
//        m_call.enqueue(new Callback<List<MatchList>>() {
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
}
