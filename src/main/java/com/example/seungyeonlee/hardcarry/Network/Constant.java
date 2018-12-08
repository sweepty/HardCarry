package com.example.seungyeonlee.hardcarry.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constant {

    public static String profileImgPath = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/{num}.png ";

    public Retrofit getRetrofit() {
        return retrofit;
    }

    // retrofit 객체 생성
    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RiotAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

//    private API api = retrofit.create(API.class);

    public static String getProfileImgPath() {
        return profileImgPath;
    }

}
