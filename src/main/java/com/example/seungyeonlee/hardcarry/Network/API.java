package com.example.seungyeonlee.hardcarry.Network;

import com.example.seungyeonlee.hardcarry.Models.Info;
import com.example.seungyeonlee.hardcarry.Models.League;
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

    @GET("match/v4/matchlists/by-account/{encryptedAccountId}?endIndex=10")
    Call<List<MatchList>> getMatchList(
            @Header("X-Riot-Token") String api_key,
            @Path("encryptedAccountId") String accountId
    );

    // https://kr.api.riotgames.com/lol/league/v4/positions/by-summoner/jYM4qsd5v9it0uQWyki1z3hFo5S1hzet3AilUQls-ssgZA
    @GET("league/v4/positions/by-summoner/{encryptedSummonerId}")
    Call<List<League>> getLeagueInfo(
            @Header("X-Riot-Token") String api_key,
            @Path("encryptedSummonerId") String encryptedSummonerId
    );

}
