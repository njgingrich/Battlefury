package com.nathan.battlefury.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.nathan.battlefury.model.AbilityUpgrade;
import com.nathan.battlefury.model.Constants;
import com.nathan.battlefury.model.GameMode;
import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathan on 4/5/15.
 */
public class DBObject {
    private SQLiteDatabase database;
    private DatabaseHandler handler;

    public DBObject(Context context) {
        handler = new DatabaseHandler(context);
    }

    public void open() throws SQLiteException {
        database = handler.getWritableDatabase();
    }

    public void close() {
        handler.close();
    }

    public AbilityUpgrade insertAbilityUpgrade(long match_id, long account_id,
                                               int ability, int time, int level) {
        AbilityUpgrade upgrade = new AbilityUpgrade();
        upgrade.ability = ability;
        upgrade.time = time;
        upgrade.level = level;

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.TableAbilityUpgrades.COL_MATCH_ID, match_id);
        values.put(DatabaseHandler.TableAbilityUpgrades.COL_ACCOUNT_ID, account_id);
        values.put(DatabaseHandler.TableAbilityUpgrades.COL_ABILITY, ability);
        values.put(DatabaseHandler.TableAbilityUpgrades.COL_TIME, time);
        values.put(DatabaseHandler.TableAbilityUpgrades.COL_LEVEL, level);

        database.insert(DatabaseHandler.TableAbilityUpgrades.TABLE_NAME, null, values);
        return upgrade;
    }

    public Player insertPlayer(long match_id,    long account_id, int player_slot,
                               int hero_id,      String items,    int kills,
                               int deaths,       int assists,     int gold,
                               int last_hits,    int denies,      int gpm,
                               int xpm,          int gold_spent,  int hero_damage,
                               int tower_damage, int hero_healing,
                               int level,        AbilityUpgrade[] upgrades) {
        Player p = new Player();
        p.setAccount_id(account_id);
        p.setPlayer_slot(player_slot);
        p.setHero_id(hero_id);
        p.setItems(items);
        p.setKills(kills);
        p.setDeaths(deaths);
        p.setAssists(assists);
        p.setGold(gold);
        p.setLast_hits(last_hits);
        p.setDenies(denies);
        p.setGpm(gpm);
        p.setXpm(xpm);
        p.setGold_spent(gold_spent);
        p.setHero_damage(hero_damage);
        p.setTower_damage(tower_damage);
        p.setHero_healing(hero_healing);
        p.setLevel(level);
        p.setUpgrades(upgrades);

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.TablePlayers.COL_MATCH_ID, match_id);
        values.put(DatabaseHandler.TablePlayers.COL_ACCOUNT_ID, account_id);
        values.put(DatabaseHandler.TablePlayers.COL_PLAYER_SLOT, player_slot);
        values.put(DatabaseHandler.TablePlayers.COL_HERO_ID, hero_id);
        values.put(DatabaseHandler.TablePlayers.COL_ITEMS, items);
        values.put(DatabaseHandler.TablePlayers.COL_KILLS, kills);
        values.put(DatabaseHandler.TablePlayers.COL_DEATHS, deaths);
        values.put(DatabaseHandler.TablePlayers.COL_ASSISTS, assists);
        values.put(DatabaseHandler.TablePlayers.COL_GOLD, gold);
        values.put(DatabaseHandler.TablePlayers.COL_LAST_HITS, last_hits);
        values.put(DatabaseHandler.TablePlayers.COL_DENIES, denies);
        values.put(DatabaseHandler.TablePlayers.COL_GPM, gpm);
        values.put(DatabaseHandler.TablePlayers.COL_XPM, xpm);
        values.put(DatabaseHandler.TablePlayers.COL_GOLD_SPENT, gold_spent);
        values.put(DatabaseHandler.TablePlayers.COL_HERO_DMG, hero_damage);
        values.put(DatabaseHandler.TablePlayers.COL_TOWER_DMG, tower_damage);
        values.put(DatabaseHandler.TablePlayers.COL_HERO_HEAL, hero_healing);
        values.put(DatabaseHandler.TablePlayers.COL_LEVEL, level);

        Log.i("Database", "Inserting player " + account_id + " for matchID " + match_id);
        database.insert(DatabaseHandler.TablePlayers.TABLE_NAME, null, values);

        // Now insert the upgrades for the player
        for (AbilityUpgrade upgrade : upgrades) {
            insertAbilityUpgrade(match_id, account_id, upgrade.ability,
                                 upgrade.time, upgrade.level);
        }

        return p;
    }

    public Match insertMatch(Player[] radiant,
                             Player[] dire,
                             boolean radiant_win,
                             int duration,
                             long start_time,
                             long match_id,
                             int tower_status_radiant,
                             int tower_status_dire,
                             int barracks_status_radiant,
                             int barracks_status_dire,
                             int cluster,
                             int first_blood_time,
                             int lobby_type,
                             int human_players,
                             GameMode game_mode) {
        Match match = new Match();
        match.setRadiant(radiant);
        match.setDire(dire);
        match.setRadiant_win(radiant_win);
        match.setDuration(duration);
        match.setStart_time(start_time);
        match.setTower_status_radiant(tower_status_radiant);
        match.setTower_status_dire(tower_status_dire);
        match.setBarracks_status_radiant(barracks_status_radiant);
        match.setBarracks_status_dire(barracks_status_dire);
        match.setCluster(cluster);
        match.setFirst_blood_time(first_blood_time);
        match.setLobby_type(lobby_type);
        match.setHuman_players(human_players);
        match.setGame_mode(game_mode);

        for (Player p : radiant) {
            insertPlayer(match_id, p.getAccount_id(), p.getPlayer_slot(),
                    p.getHero_id(), p.getItemsCSV(), p.getKills(), p.getDeaths(),
                    p.getAssists(), p.getGold(), p.getLast_hits(), p.getDenies(),
                    p.getGpm(), p.getXpm(), p.getGold_spent(), p.getHero_damage(),
                    p.getTower_damage(), p.getHero_healing(), p.getLevel(),
                    p.getUpgrades());
        }
        for (Player p : dire) {
            insertPlayer(match_id, p.getAccount_id(), p.getPlayer_slot(),
                    p.getHero_id(), p.getItemsCSV(), p.getKills(), p.getDeaths(),
                    p.getAssists(), p.getGold(), p.getLast_hits(), p.getDenies(),
                    p.getGpm(), p.getXpm(), p.getGold_spent(), p.getHero_damage(),
                    p.getTower_damage(), p.getHero_healing(), p.getLevel(),
                    p.getUpgrades());
        }
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.TableMatches.COL_MATCH_ID, match_id);
        values.put(DatabaseHandler.TableMatches.COL_PLAYER1, radiant[1].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER2, radiant[2].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER3, radiant[3].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER4, radiant[4].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER5, radiant[5].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER6, dire[1].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER7, dire[2].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER8, dire[3].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER9, dire[4].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER10, dire[5].toString());
        values.put(DatabaseHandler.TableMatches.COL_RADIANT_WIN, radiant_win);
        values.put(DatabaseHandler.TableMatches.COL_DURATION, duration);
        values.put(DatabaseHandler.TableMatches.COL_START_TIME, start_time);
        values.put(DatabaseHandler.TableMatches.COL_TOWERS_RADIANT, tower_status_radiant);
        values.put(DatabaseHandler.TableMatches.COL_TOWERS_DIRE, tower_status_dire);
        values.put(DatabaseHandler.TableMatches.COL_BARRACKS_RADIANT, barracks_status_radiant);
        values.put(DatabaseHandler.TableMatches.COL_BARRACKS_DIRE, barracks_status_dire);
        values.put(DatabaseHandler.TableMatches.COL_CLUSTER, cluster);
        values.put(DatabaseHandler.TableMatches.COL_FIRST_BLOOD_TIME, first_blood_time);
        values.put(DatabaseHandler.TableMatches.COL_LOBBY_TYPE, lobby_type);
        values.put(DatabaseHandler.TableMatches.COL_HUMAN_PLAYERS, human_players);
        values.put(DatabaseHandler.TableMatches.COL_GAME_MODE, game_mode.ordinal());
        Log.i("Database", "Inserted match " + match_id);
        return match;
    }

    public Match insertMatch(Match match) {
    for (Player p : match.getRadiant()) {
            insertPlayer(match.getMatch_id(), p.getAccount_id(), p.getPlayer_slot(),
                    p.getHero_id(), p.getItemsCSV(), p.getKills(), p.getDeaths(),
                    p.getAssists(), p.getGold(), p.getLast_hits(), p.getDenies(),
                    p.getGpm(), p.getXpm(), p.getGold_spent(), p.getHero_damage(),
                    p.getTower_damage(), p.getHero_healing(), p.getLevel(),
                    p.getUpgrades());
        }
        for (Player p : match.getDire()) {
            insertPlayer(match.getMatch_id(), p.getAccount_id(), p.getPlayer_slot(),
                    p.getHero_id(), p.getItemsCSV(), p.getKills(), p.getDeaths(),
                    p.getAssists(), p.getGold(), p.getLast_hits(), p.getDenies(),
                    p.getGpm(), p.getXpm(), p.getGold_spent(), p.getHero_damage(),
                    p.getTower_damage(), p.getHero_healing(), p.getLevel(),
                    p.getUpgrades());
        }
        Player[] radiant = match.getRadiant();
        Player[] dire = match.getDire();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.TableMatches.COL_MATCH_ID, match.getMatch_id());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER1, radiant[1].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER2, radiant[2].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER3, radiant[3].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER4, radiant[4].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER5, radiant[5].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER6, dire[1].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER7, dire[2].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER8, dire[3].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER9, dire[4].toString());
        values.put(DatabaseHandler.TableMatches.COL_PLAYER10, dire[5].toString());
        values.put(DatabaseHandler.TableMatches.COL_RADIANT_WIN, match.isRadiant_win());
        values.put(DatabaseHandler.TableMatches.COL_DURATION, match.getDuration());
        values.put(DatabaseHandler.TableMatches.COL_START_TIME, match.getStart_time());
        values.put(DatabaseHandler.TableMatches.COL_TOWERS_RADIANT, match.getTower_status_radiant());
        values.put(DatabaseHandler.TableMatches.COL_TOWERS_DIRE, match.getTower_status_dire());
        values.put(DatabaseHandler.TableMatches.COL_BARRACKS_RADIANT, match.getBarracks_status_radiant());
        values.put(DatabaseHandler.TableMatches.COL_BARRACKS_DIRE, match.getBarracks_status_dire());
        values.put(DatabaseHandler.TableMatches.COL_CLUSTER, match.getCluster());
        values.put(DatabaseHandler.TableMatches.COL_FIRST_BLOOD_TIME, match.getFirst_blood_time());
        values.put(DatabaseHandler.TableMatches.COL_LOBBY_TYPE, match.getLobby_type());
        values.put(DatabaseHandler.TableMatches.COL_HUMAN_PLAYERS, match.getHuman_players());
        values.put(DatabaseHandler.TableMatches.COL_GAME_MODE, match.getGame_mode().ordinal());
        Log.i("Database", "Inserted match " + match.getMatch_id());
        return match;
    }

    public List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<>();

        Cursor matchCursor = database.query(DatabaseHandler.TableMatches.TABLE_NAME,
                                       null, null, null, null, null, null);
        matchCursor.moveToFirst();
        while (!matchCursor.isAfterLast()) {
            Match match = cursorToMatch(matchCursor);
            matches.add(match);
            matchCursor.moveToNext();
        }
        matchCursor.close();
        return matches;
    }

    private Match cursorToMatch(Cursor cursor) {
        Match match = new Match();
        List<Player> players = new ArrayList<>();
        long match_id = cursor.getLong(cursor.getColumnIndex("match_id"));
        Cursor playerCursor = database.query(DatabaseHandler.TablePlayers.TABLE_NAME,
                                             new String[] { "account_id" },
                                             DatabaseHandler.TablePlayers.COL_MATCH_ID + " = ?",
                                             new String[] { "" + match_id },
                                             null, null, null);
        playerCursor.moveToFirst();
        while (!playerCursor.isAfterLast()) {
            Player player = cursorToPlayer(playerCursor, match_id);
            players.add(player);
            playerCursor.moveToNext();
        }
        return match;
    }

    private Player cursorToPlayer(Cursor cursor, long match_id) {
        Player player = new Player();
        player.setAccount_id(cursor.getLong(cursor.getColumnIndex("account_id")));
        player.setPlayer_slot(cursor.getInt(cursor.getColumnIndex("player_slot")));
        player.setHero_id(cursor.getInt(cursor.getColumnIndex("hero_id")));
        player.setItems(cursor.getString(cursor.getColumnIndex("items")));
        player.setKills(cursor.getInt(cursor.getColumnIndex("kills")));
        player.setDeaths(cursor.getInt(cursor.getColumnIndex("deaths")));
        player.setAssists(cursor.getInt(cursor.getColumnIndex("assists")));
        player.setGold(cursor.getInt(cursor.getColumnIndex("gold")));
        player.setLast_hits(cursor.getInt(cursor.getColumnIndex("last_hits")));
        player.setDenies(cursor.getInt(cursor.getColumnIndex("denies")));
        player.setGpm(cursor.getInt(cursor.getColumnIndex("gpm")));
        player.setXpm(cursor.getInt(cursor.getColumnIndex("xpm")));
        player.setGold_spent(cursor.getInt(cursor.getColumnIndex("gold_spent")));
        player.setHero_damage(cursor.getInt(cursor.getColumnIndex("hero_damage")));
        player.setTower_damage(cursor.getInt(cursor.getColumnIndex("tower_damage")));
        player.setHero_healing(cursor.getInt(cursor.getColumnIndex("hero_healing")));
        player.setLevel(cursor.getInt(cursor.getColumnIndex("level")));
        // Get abilities for that player + match
        AbilityUpgrade[] upgrades = new AbilityUpgrade[25];
        Cursor abilityCursor = database.query(DatabaseHandler.TablePlayers.TABLE_NAME,
                               null,
                               DatabaseHandler.TableAbilityUpgrades.COL_MATCH_ID + " = ? AND " +
                               DatabaseHandler.TableAbilityUpgrades.COL_ACCOUNT_ID + " = ?",
                               new String[] { "" + match_id, "" + player.getAccount_id() },
                               null, null, null);
        abilityCursor.moveToFirst();
        int i = 0;
        while (!abilityCursor.isAfterLast()) {
            upgrades[i] = cursorToUpgrade(abilityCursor);
            i++;
            abilityCursor.moveToNext();
        }
        while (i < Constants.MAX_LEVEL) {
            upgrades[i] = new AbilityUpgrade();
            i++;
        }
        return player;
    }

    private AbilityUpgrade cursorToUpgrade(Cursor cursor) {
        AbilityUpgrade upgrade = new AbilityUpgrade();
        upgrade.ability = cursor.getInt(cursor.getInt(cursor.getColumnIndex("ability")));
        upgrade.time    = cursor.getInt(cursor.getInt(cursor.getColumnIndex("time")));
        upgrade.level   = cursor.getInt(cursor.getInt(cursor.getColumnIndex("level")));

        return upgrade;
    }
}

