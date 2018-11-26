package com.example.seungyeonlee.hardcarry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {
    /*
        "profileIconId": 6,
        "name": "Hide on bush",
        "summonerLevel": 168,
        "accountId": 3440481,
        "id": 4460427,
        "revisionDate": 1542005949000
    */

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public String getName() {
        return name;
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

    @SerializedName("profileIconId")
    @Expose
    private Integer profileIconId;

    @SerializedName("name")
    @Expose
    private String name;

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
