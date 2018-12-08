package com.example.seungyeonlee.hardcarry.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Match {
    /*

    "matches": [
        {
            "lane": "MID",
            "gameId": 3443856532,
            "champion": 8,
            "platformId": "KR",
            "timestamp": 1543813222332,
            "queue": 420,
            "role": "DUO_CARRY",
            "season": 11
        },
    */
    @SerializedName("lane")
    @Expose
    private String lane;

    @SerializedName("gameId")
    @Expose
    private String gameId;

    @SerializedName("champion")
    @Expose
    private Integer champion;

    @SerializedName("platformId")
    @Expose
    private String platformId;

    @SerializedName("timestamp")
    @Expose
    private Timestamp timestamp;

    @SerializedName("queue")
    @Expose
    private Integer queue;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("season")
    @Expose
    private Integer season;

    public String getLane() {
        return lane;
    }

    public String getGameId() {
        return gameId;
    }

    public Integer getChampion() {
        return champion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getQueue() {
        return queue;
    }

    public String getRole() {
        return role;
    }

    public Integer getSeason() {
        return season;
    }
}
