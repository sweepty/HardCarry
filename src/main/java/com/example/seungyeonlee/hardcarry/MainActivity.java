package com.example.seungyeonlee.hardcarry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seungyeonlee.hardcarry.Network.API;
import com.example.seungyeonlee.hardcarry.Network.Key;
import com.example.seungyeonlee.hardcarry.Network.RiotAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = (TextView) findViewById(R.id.editText);
        searchBtn = (Button) findViewById(R.id.button);

        // 조회 버튼 클릭
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String summonerName = inputTextView.getText().toString();

                // Retrofit object 생성 및 api를 통한 클래스 객체 생성
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RiotAPI.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Defining retrofit api service
                API api = retrofit.create(API.class);

                // id와 password가 담긴 request를 날려서 php에서 데이터를 response 받음
                Call<Info> call = api.getSummonersInfo(
                        Key.getApiKey().toString(), summonerName.toString()
                );

                // response 받는 부분
                call.enqueue(new Callback<Info>() {

                    // 응답
                    @Override
                    public void onResponse(Call<Info> call, Response<Info> response) {
//                        progressDialog.dismiss();
                        System.out.println("@@@@@@@@@@@@@@@@");
                        Toast.makeText(getApplicationContext(), "success"+response.body().getName(), Toast.LENGTH_LONG).show();
                        System.out.println(response.body().getName());
                    }

                    @Override
                    public void onFailure(Call<Info> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }

//                    // 실패 시
//                    @Override
//                    public void onFailure(Call<Info> call, Throwable t) {
////                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                });
            }
        });

    }


}
