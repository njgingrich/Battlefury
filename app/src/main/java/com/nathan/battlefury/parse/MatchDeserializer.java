package com.nathan.battlefury.parse;

import android.content.Context;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.nathan.battlefury.model.AbilityUpgrade;
import com.nathan.battlefury.model.Constants;
import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by nathan on 3/31/15.
 */
public class MatchDeserializer implements JsonDeserializer {
    private Context context;
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Match match = new Match();
        ArrayList<Player> players = new ArrayList<>();
        JsonObject obj = (JsonObject) json;

        if (obj.has("result")) {
            obj = (JsonObject) obj.get("result");
            JsonArray playerArray = (JsonArray) obj.get("players");

            match.radiant_win             = obj.get("radiant_win").getAsBoolean();
            match.duration                = obj.get("duration").getAsInt();
            match.start_time              = obj.get("start_time").getAsLong();
            match._id                     = obj.get("match_id").getAsLong();
            match.tower_status_radiant    = obj.get("tower_status_radiant").getAsInt();
            match.tower_status_dire       = obj.get("tower_status_dire").getAsInt();
            match.barracks_status_radiant = obj.get("barracks_status_radiant").getAsInt();
            match.barracks_status_dire    = obj.get("barracks_status_dire").getAsInt();
            match.cluster                 = obj.get("cluster").getAsInt();
            match.first_blood_time        = obj.get("first_blood_time").getAsInt();
            match.lobby_type              = obj.get("lobby_type").getAsInt();
            match.human_players           = obj.get("human_players").getAsInt();
            match.game_mode               = obj.get("game_mode").getAsInt();

            for (int i = 0; i < playerArray.size(); i++) {
                Player p = getPlayer(playerArray, i, match._id);
                players.add(p);
            }
            for (Player p : players) {
                switch (p.player_slot) {
                    case 0:   match.player1  = p._id;
                    case 1:   match.player2  = p._id;
                    case 2:   match.player3  = p._id;
                    case 3:   match.player4  = p._id;
                    case 4:   match.player5  = p._id;
                    case 128: match.player6  = p._id;
                    case 129: match.player7  = p._id;
                    case 130: match.player8  = p._id;
                    case 131: match.player9  = p._id;
                    case 132: match.player10 = p._id;
                }
            }
        }

        return match;
    }

    // BAD
    private Player getPlayer(JsonArray playerArray, int i, long match_id) {
        int[] items = new int[6];
        Player p = new Player();

        JsonObject player = (JsonObject) playerArray.get(i);
        Log.i("JSON SHIT", "player: " + player.toString());
        p._id          = player.get("account_id").getAsInt();
        p.player_slot  = player.get("player_slot").getAsInt();
        p.hero_id      = player.get("hero_id").getAsInt();
        for (int j = 0; j < items.length; j++) {
            items[j] = player.get("item_" + j).getAsInt();
        }
        p.items        = setItems(items);
        p.kills        = player.get("kills").getAsInt();
        p.deaths       = player.get("deaths").getAsInt();
        p.assists      = player.get("assists").getAsInt();
        p.gold         = player.get("gold").getAsInt();
        p.last_hits    = player.get("last_hits").getAsInt();
        p.denies       = player.get("denies").getAsInt();
        p.gpm          = player.get("gold_per_min").getAsInt();
        p.xpm          = player.get("xp_per_min").getAsInt();
        p.gold_spent   = player.get("gold_spent").getAsInt();
        p.hero_damage  = player.get("hero_damage").getAsInt();
        p.tower_damage = player.get("tower_damage").getAsInt();
        p.hero_healing = player.get("hero_healing").getAsInt();
        p.level        = player.get("level").getAsInt();
        p.upgrades     = getAbilityUpgrades(p, (JsonArray) player.get("ability_upgrades"), match_id);
        return p;
    }

    private AbilityUpgrade[] getAbilityUpgrades(Player p, JsonArray abilityUpgradesArray, long match_id) {
        AbilityUpgrade[] abilityUpgrades = new AbilityUpgrade[25];
        int index;
        for (index = 0; index < abilityUpgradesArray.size()-1; index++) {
            abilityUpgrades[index] = new AbilityUpgrade();
            JsonObject upgrade = (JsonObject) abilityUpgradesArray.get(index);

            abilityUpgrades[index]._id     = p.player_slot + match_id;
            abilityUpgrades[index].ability = upgrade.get("ability").getAsInt();
            abilityUpgrades[index].time    = upgrade.get("time").getAsInt();
            abilityUpgrades[index].level   = upgrade.get("level").getAsInt();
        }
        while (index < Constants.MAX_LEVEL) {
            abilityUpgrades[index] = new AbilityUpgrade();
            index++;
        }
        return abilityUpgrades;
    }

    private String setItems(int[] items) {
        StringBuilder sb = new StringBuilder();
        for (int item : items) {
            sb.append(item);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1); // remove last comma
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1): "";
    }
}
