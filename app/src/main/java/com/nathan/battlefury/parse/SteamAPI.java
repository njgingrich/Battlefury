package com.nathan.battlefury.parse;

import com.nathan.battlefury.model.Match;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by nathan on 3/29/15.
 */
public interface SteamAPI {

    /* Get single match */
    @GET("/GetMatchDetails/V001/")
    public void getMatch(@Query("key") String key, @Query("match_id") long match_id, Callback<Match> callback);
}
