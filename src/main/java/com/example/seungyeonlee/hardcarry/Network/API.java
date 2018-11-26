package com.example.seungyeonlee.hardcarry.Network;

import com.example.seungyeonlee.hardcarry.Info;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface API {

    // 소환사명 조회 1
    @GET("summoner/v3/summoners/by-name/{summonerName}")
    Call<Info> getSummonersInfo(
            @Header("X-Riot-Token") String api_key,
            @Path("summonerName") String summonerName
    );

}
