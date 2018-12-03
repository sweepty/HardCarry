package com.example.seungyeonlee.hardcarry.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    /* v3
        "profileIconId": 6,
        "name": "Hide on bush",
        "summonerLevel": 168,
        "accountId": 3440481,
        "id": 4460427,
        "revisionDate": 1542005949000
    */
    /*  v4
        "profileIconId": 6,
        "name": "Hide on bush",
        "puuid": "TEQsvB3bqpg8839daj0igsCzHzo5oOMIf-YMkuRW2sKj_eCW4LVw6U_QbZnxc_KcXPLXAQjEbfvVaA",
        "summonerLevel": 172,
        "accountId": "hBt96CHvqMAE2Vq1U7vcnM2HSLkexk3yHtR5FUs0cORA",
        "id": "jYM4qsd5v9it0uQWyki1z3hFo5S1hzet3AilUQls-ssgZA",
        "revisionDate": 1543786615000

    */


    public Integer getProfileIconId() {
        return profileIconId;
    }

    public String getName() {
        return name;
    }

    public String getPuuid() {
        return puuid;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getId() {
        return id;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    @SerializedName("profileIconId")
    @Expose
    private Integer profileIconId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("puuid")
    @Expose
    private String puuid;

    @SerializedName("summonerLevel")
    @Expose
    private Integer summonerLevel;

    @SerializedName("accountId")
    @Expose
    private String accountId;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("revisionDate")
    @Expose
    private String revisionDate;

}
