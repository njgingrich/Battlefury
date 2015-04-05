package com.nathan.battlefury.parse;

import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.nathan.battlefury.model.AbilityUpgrade;
import com.nathan.battlefury.model.GameMode;
import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by nathan on 3/31/15.
 */
public class MatchDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Match match = new Match();
        ArrayList<Player> players = new ArrayList<>();
        JsonObject obj = (JsonObject) json;

        if (obj.has("result")) {
            obj = (JsonObject) obj.get("result");
            JsonArray playerArray = (JsonArray) obj.get("players");
            GameMode[] modes = GameMode.values();

            for (int i = 0; i < playerArray.size(); i++) {
                int[] items = new int[6];
                AbilityUpgrade[] abilityUpgrades = new AbilityUpgrade[25];
                Player p = new Player();

                JsonObject player = (JsonObject) playerArray.get(i);
                Log.i("JSON SHIT", "player: " + player.toString());
                p.setAccount_id(player.get("account_id").getAsInt());
                p.setPlayer_slot(player.get("player_slot").getAsInt());
                p.setHero_id(player.get("hero_id").getAsInt());
                for (int j = 0; j < items.length; j++) {
                    items[j] = player.get("item_" + j).getAsInt();
                }
                p.setItems(items);
                p.setKills(player.get("kills").getAsInt());
                p.setDeaths(player.get("deaths").getAsInt());
                p.setAssists(player.get("assists").getAsInt());
                p.setGold(player.get("gold").getAsInt());
                p.setLast_hits(player.get("last_hits").getAsInt());
                p.setDenies(player.get("denies").getAsInt());
                p.setGpm(player.get("gold_per_min").getAsInt());
                p.setXpm(player.get("xp_per_min").getAsInt());
                p.setGold_spent(player.get("gold_spent").getAsInt());
                p.setHero_damage(player.get("hero_damage").getAsInt());
                p.setTower_damage(player.get("tower_damage").getAsInt());
                p.setHero_healing(player.get("hero_healing").getAsInt());
                p.setLevel(player.get("level").getAsInt());

                JsonArray abilityUpgradesArray = (JsonArray) player.get("ability_upgrades");
                for (int j = 0; j < abilityUpgradesArray.size(); j++) {
                    abilityUpgrades[j] = new AbilityUpgrade();
                    JsonObject upgrade = (JsonObject) abilityUpgradesArray.get(j);
                    abilityUpgrades[j].ability = upgrade.get("ability").getAsInt();
                    abilityUpgrades[j].time = upgrade.get("time").getAsInt();
                    abilityUpgrades[j].level = upgrade.get("level").getAsInt();
                }
                p.setUpgrades(abilityUpgrades);
                players.add(p);
            }
            ArrayList<Player> radiant = new ArrayList<>(5);
            ArrayList<Player> dire    = new ArrayList<>(5);
            for (Player p : players) {
                if (p.getPlayer_slot() < 128) {
                    radiant.add(p);
                } else {
                    dire.add(p);
                }
            }
            Player[] radiantTeam = new Player[5];
            Player[] direTeam    = new Player[5];
            match.setRadiant(radiant.toArray(radiantTeam));
            match.setDire(dire.toArray(direTeam));

            match.setRadiant_win(obj.get("radiant_win").getAsBoolean());
            match.setDuration(obj.get("duration").getAsInt());
            match.setStart_time(obj.get("start_time").getAsLong());
            match.setMatch_id(obj.get("match_id").getAsLong());
            match.setTower_status_radiant(obj.get("tower_status_radiant").getAsInt());
            match.setTower_status_dire(obj.get("tower_status_dire").getAsInt());
            match.setBarracks_status_radiant(obj.get("barracks_status_radiant").getAsInt());
            match.setBarracks_status_dire(obj.get("barracks_status_dire").getAsInt());
            match.setCluster(obj.get("cluster").getAsInt());
            match.setFirst_blood_time(obj.get("first_blood_time").getAsInt());
            match.setLobby_type(obj.get("lobby_type").getAsInt());
            match.setHuman_players(obj.get("human_players").getAsInt());
            match.setGame_mode( modes[obj.get("game_mode").getAsInt()] );
        }

        return match;
    }
}
