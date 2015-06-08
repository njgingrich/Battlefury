package com.nathan.battlefury;

import android.app.Application;
import android.util.Log;

import com.nathan.battlefury.parse.RestClient;
import com.nathan.battlefury.parse.SteamAPI;
import com.nathan.battlefury.rest.BusProvider;
import com.nathan.battlefury.rest.MatchService;
import com.nathan.battlefury.rest.event.ApiErrorEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by nathan on 6/3/15.
 */
public class BattlefuryApplication extends Application {
    private MatchService service;
    private Bus bus = BusProvider.getInstance();

    @Override
    public void onCreate() {
        super.onCreate();

        service = new MatchService(buildApi(), bus);
        bus.register(service);

        bus.register(this); //listen for "global" events
    }

    private SteamAPI buildApi() {
        return RestClient.get();
    }

    @Subscribe
    public void onApiError(ApiErrorEvent event) {
        //toast("Something went wrong, please try again.");
        Log.e("BattlefuryApp", event.getErrorMessage());
    }
}
