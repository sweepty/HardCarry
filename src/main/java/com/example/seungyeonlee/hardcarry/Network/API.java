package com.example.seungyeonlee.hardcarry.Network;

import com.example.seungyeonlee.hardcarry.Models.Info;
import com.example.seungyeonlee.hardcarry.Models.MatchList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface API {

    // 소환사명 조회 1
    @GET("summoner/v4/summoners/by-name/{summonerName}")
    Call<Info> getSummonersInfo(
            @Header("X-Riot-Token") String api_key,
            @Path("summonerName") String summonerName
    );

    // 소환사명 조회 2(최근 경기)
    @GET("match/v4/matchlists/by-account/{encryptedAccountId}")
    Call<List<MatchList>> getMatchList(
            @Header("X-Riot-Token") String api_key,
            @Path("encryptedAccountId") String accountId
    );

}
