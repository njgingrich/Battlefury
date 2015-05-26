package com.nathan.battlefury.parse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nathan.battlefury.model.Constants;
import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.MatchHistory;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by nathan on 3/31/15.
 */
public class RestClient {
    private static SteamAPI REST_CLIENT;

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static SteamAPI get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd")
                .registerTypeAdapter(Match.class, new MatchDeserializer())
                .registerTypeAdapter(MatchHistory.class, new MatchHistoryDeserializer())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson))
                .setEndpoint(Constants.API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        REST_CLIENT = restAdapter.create(SteamAPI.class);
    }
}
