package com.nathan.battlefury.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nathan.battlefury.model.AbilityUpgrade;
import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.Player;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by nathan on 3/29/15.
 */
public class SQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "matches.db";

    /*private static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + TableMatches.TABLE_NAME + ", " +
                                      TablePlayers.TABLE_NAME + ", " +
                                      TableAbilityUpgrades.TABLE_NAME;

    private static final String SQL_CLEAR_TABLES =
            "DELETE FROM " + TableMatches.TABLE_NAME + ";" +
            "DELETE FROM " + TablePlayers.TABLE_NAME + ";" +
            "DELETE FROM " + TableAbilityUpgrades.TABLE_NAME + ";";*/

    static {
        // register our models
        cupboard().register(Player.class);
        cupboard().register(Match.class);
        cupboard().register(AbilityUpgrade.class);
    }

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /*public void emptyTables(SQLiteDatabase db) {
        Log.i("Database", "Emptied all tables.");
        db.execSQL(SQL_CLEAR_TABLES);
    }

    public static abstract class TableMatches {
        public static final String TABLE_NAME = "matches";

        public static final String COL_PLAYER1  = "player1";
        public static final String COL_PLAYER2  = "player2";
        public static final String COL_PLAYER3  = "player3";
        public static final String COL_PLAYER4  = "player4";
        public static final String COL_PLAYER5  = "player5";
        public static final String COL_PLAYER6  = "player6";
        public static final String COL_PLAYER7  = "player7";
        public static final String COL_PLAYER8  = "player8";
        public static final String COL_PLAYER9  = "player9";
        public static final String COL_PLAYER10 = "player10";

        public static final String COL_RADIANT_WIN = "radiant_win";
        public static final String COL_DURATION    = "duration";
        public static final String COL_START_TIME  = "start_time";
        public static final String COL_MATCH_ID    = "match_id";
        public static final String COL_TOWERS_RADIANT = "towers_status_radiant";
        public static final String COL_TOWERS_DIRE    = "towers_status_dire";
        public static final String COL_BARRACKS_RADIANT = "barracks_status_radiant";
        public static final String COL_BARRACKS_DIRE    = "barracks_status_dire";
        public static final String COL_CLUSTER = "cluster";
        public static final String COL_FIRST_BLOOD_TIME = "first_blood_time";
        public static final String COL_LOBBY_TYPE       = "lobby_type";
        public static final String COL_HUMAN_PLAYERS    = "human_players";
        public static final String COL_GAME_MODE        = "game_mode";
    }

    public static abstract class TablePlayers {
        public static final String TABLE_NAME = "match_players";

        public static final String COL_MATCH_ID    = "match_id";
        public static final String COL_ACCOUNT_ID  = "account_id";
        public static final String COL_PLAYER_SLOT = "player_slot";
        public static final String COL_HERO_ID     = "hero_id";
        public static final String COL_ITEMS       = "items";
        public static final String COL_KILLS       = "kills";
        public static final String COL_DEATHS      = "deaths";
        public static final String COL_ASSISTS     = "assists";
        public static final String COL_GOLD        = "gold";
        public static final String COL_LAST_HITS   = "last_hits";
        public static final String COL_DENIES      = "denies";
        public static final String COL_GPM         = "gpm";
        public static final String COL_XPM         = "xpm";
        public static final String COL_GOLD_SPENT  = "gold_spent";
        public static final String COL_HERO_DMG    = "hero_damage";
        public static final String COL_TOWER_DMG   = "tower_damage";
        public static final String COL_HERO_HEAL   = "hero_healing";
        public static final String COL_LEVEL       = "level";
        public static final String COL_UPGRADES    = "upgrades";
    }

    public static abstract class TableAbilityUpgrades {
        public static final String TABLE_NAME = "abilityUpgrades";

        public static final String COL_MATCH_ID   = "match_id";
        public static final String COL_ACCOUNT_ID = "account_id";
        public static final String COL_ABILITY    = "ability";
        public static final String COL_TIME       = "time";
        public static final String COL_LEVEL      = "level";
    }*/
}
