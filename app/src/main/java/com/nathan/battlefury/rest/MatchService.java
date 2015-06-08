package com.nathan.battlefury.rest;

import android.util.Log;
import android.widget.Toast;

import com.nathan.battlefury.model.Constants;
import com.nathan.battlefury.model.MatchHistory;
import com.nathan.battlefury.parse.SteamAPI;
import com.nathan.battlefury.rest.event.ApiErrorEvent;
import com.nathan.battlefury.rest.event.LoadMatchesEvent;
import com.nathan.battlefury.rest.event.MatchesLoadedEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nathan on 6/3/15.
 */
public class MatchService {
    private SteamAPI api;
    private Bus bus;

    public MatchService(SteamAPI api, Bus bus) {
        this.api = api;
        this.bus = bus;
    }

    @Subscribe
    public void onLoadMatches(LoadMatchesEvent event) {
        Log.i("Otto", "found loadMatchesEvent");
        api.getMatchHistory(Constants.STEAM_KEY, 0L, new Callback<MatchHistory>() {
            @Override
            public void success(MatchHistory matchHistory, Response response) {
                bus.post(new MatchesLoadedEvent(matchHistory));
                Log.i("GetMatchHistory", "Successfully found " + matchHistory.total_matches + " matches for player");
                Log.i("GetMatchHistory", "matches size: " + matchHistory.matches.size());
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
            }
        });
    }
}


