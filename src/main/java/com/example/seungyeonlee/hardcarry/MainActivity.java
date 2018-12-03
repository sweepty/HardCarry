package com.example.seungyeonlee.hardcarry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seungyeonlee.hardcarry.Models.Info;
import com.example.seungyeonlee.hardcarry.Models.MatchList;
import com.example.seungyeonlee.hardcarry.Network.API;
import com.example.seungyeonlee.hardcarry.Network.Key;
import com.example.seungyeonlee.hardcarry.Network.RiotAPI;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;
    private Button searchBtn;
    private String userAccountId, name;
    private Integer profileIconId;

    private List matchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = (TextView) findViewById(R.id.editText);
        searchBtn = (Button) findViewById(R.id.button);

        // 프로필 이미지 사진 로드


        // 조회 버튼 클릭
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 조회하려는 소환사 이름
                String summonerName = inputTextView.getText().toString();

                // retrofit 객체 생성
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RiotAPI.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API api = retrofit.create(API.class);

                Call<Info> call = api.getSummonersInfo(
                        Key.getApiKey(), summonerName
                );

                // response 받는 부분
                call.enqueue(new Callback<Info>() {

                    // 응답
                    @Override
                    public void onResponse(Call<Info> call, Response<Info> response) {
                        System.out.println("@@@@@@@@@@@@@@@@");
                        userAccountId = response.body().getAccountId();
                        profileIconId = response.body().getProfileIconId();
                        name = response.body().getName();
                        Toast.makeText(getApplicationContext(), "success"+response.body().getName(), Toast.LENGTH_SHORT).show();
                        System.out.println(response.body().getName());
                    }

                    @Override
                    public void onFailure(Call<Info> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }

                });

                // 다음 뷰로 데이터 넘겨주기

                Intent intent = new Intent(MainActivity.this, DisplayResultActivity.class);
//                EditText editText = (EditText) findViewById(R.id.editText);
//                String message = editText.getText().toString();
                intent.putExtra("summonerAccountId", userAccountId);
                intent.putExtra("name", name);
                intent.putExtra("profileIconId", profileIconId);
                startActivity(intent);


            }
        });

    }


}
