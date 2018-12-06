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
    private String userAccountId, name, encryptedSummonerId;
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

                if (summonerName.matches("")) {
                    Toast.makeText(getApplicationContext(), "소환사명을 입력해주세요!", Toast.LENGTH_LONG).show();

                } else {
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

                            // 데이터 있는지 확인
                            if (response.isSuccessful() && response.body() != null) {

                                // 넘겨줄 데이터들..
                                userAccountId = response.body().getAccountId();
                                profileIconId = response.body().getProfileIconId();
                                name = response.body().getName();
                                encryptedSummonerId = response.body().getId();

                                Toast.makeText(getApplicationContext(), "success"+response.body().getName(), Toast.LENGTH_SHORT).show();

                                // 다음 뷰로 데이터 넘겨주기

                                Intent intent = new Intent(MainActivity.this, DisplayResultActivity.class);

                                intent.putExtra("summonerAccountId", userAccountId);
                                intent.putExtra("name", name);
                                intent.putExtra("profileIconId",profileIconId);
                                intent.putExtra("encryptedSummonerId", encryptedSummonerId);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "존재하지 않는 소환사 이름입니다.", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Info> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                        }

                    });
                }


            }
        });

    }


}
