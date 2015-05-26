package com.nathan.battlefury.parse;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.nathan.battlefury.model.MatchHistory;

import java.lang.reflect.Type;

/**
 * Created by nathan on 5/26/15.
 */
public class MatchHistoryDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        MatchHistory history = new MatchHistory();
        JsonObject obj = (JsonObject) json;

        if (obj.has("result")) {
            obj = (JsonObject) obj.get("result");
            JsonArray matchArray = (JsonArray) obj.get("matches");

            for (int i = 0; i < matchArray.size(); i++) {
                JsonObject o = (JsonObject) matchArray.get(i);
                Log.i("GetMatchHistory", "items in jsonobject: " + o.toString());
                history.matches.add(o.get("match_id").getAsLong());
            }
            history.total_matches = obj.get("num_results").getAsInt();
        }

        return history;
    }
}
