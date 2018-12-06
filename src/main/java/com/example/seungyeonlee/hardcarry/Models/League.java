package com.example.seungyeonlee.hardcarry.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class League {
    /*
        [
            {
                "queueType": "RANKED_SOLO_5x5",
                "summonerName": "Hide on bush",
                "wins": 879,
                "losses": 813,
                "rank": "I",
                "leagueName": "Kassadin's Shadowdancers",
                "leagueId": "d8d1bd86-b2e2-3aba-ac79-ee1e0170aea8",
                "tier": "MASTER",
                "summonerId": "jYM4qsd5v9it0uQWyki1z3hFo5S1hzet3AilUQls-ssgZA",
                "leaguePoints": 550
            },~~~
        ]
    */
    @SerializedName("queueType")
    @Expose
    private String queueType;

    @SerializedName("summonerName")
    @Expose
    private String summonerName;

    @SerializedName("wins")
    @Expose
    private Integer wins;

    @SerializedName("losses")
    @Expose
    private Integer losses;

    @SerializedName("rank")
    @Expose
    private String rank;

    @SerializedName("leagueName")
    @Expose
    private String leagueName;

    @SerializedName("leagueId")
    @Expose
    private String leagueId;

    @SerializedName("tier")
    @Expose
    private String tier;

    @SerializedName("summonerId")
    @Expose
    private String summonerId;

    @SerializedName("leaguePoints")
    @Expose
    private Integer leaguePoints;


    public String getQueueType() {
        return queueType;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public String getRank() {
        return rank;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getTier() {
        return tier;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }
}
