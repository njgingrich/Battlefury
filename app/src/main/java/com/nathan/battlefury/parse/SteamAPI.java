package com.nathan.battlefury.parse;

import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.MatchHistory;

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

    //TODO: currently only retrieves 25 matches, make it get all of them

    //NOTE: Will only get 500 matches (if >500, will need to get for each hero (max 500 for ea hero?)
    /* Get match history for player */
    @GET("/GetMatchHistory/v1/")
    public void getMatchHistory(@Query("key") String key, @Query("start_at_match_id") long match_id, Callback<MatchHistory> callback);
}
