package com.nathan.battlefury.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nathan on 3/29/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "matches.db";

    public static final String COMMA_SEP     = ", ";
    public static final String SQL_CREATE_DB =
            "CREATE TABLE " + TableMatches.TABLE_NAME + " (" +
            TableMatches.COL_MATCH_ID + " INTEGER PRIMARY KEY," +
            TableMatches.COL_PLAYER1 + " BIGINT(11), " +
            TableMatches.COL_PLAYER2 + " BIGINT(11), " +
            TableMatches.COL_PLAYER3 + " BIGINT(11), " +
            TableMatches.COL_PLAYER4 + " BIGINT(11), " +
            TableMatches.COL_PLAYER5 + " BIGINT(11), " +
            TableMatches.COL_PLAYER6 + " BIGINT(11), " +
            TableMatches.COL_PLAYER7 + " BIGINT(11), " +
            TableMatches.COL_PLAYER8 + " BIGINT(11), " +
            TableMatches.COL_PLAYER9 + " BIGINT(11), " +
            TableMatches.COL_PLAYER10 + " BIGINT(11), " +
            TableMatches.COL_RADIANT_WIN + " BIT, " +
            TableMatches.COL_DURATION + " BIGINT, " +
            TableMatches.COL_START_TIME + " DATETIME, " +
            TableMatches.COL_TOWERS_RADIANT + " INT, " +
            TableMatches.COL_TOWERS_DIRE + " INT, " +
            TableMatches.COL_BARRACKS_RADIANT + " INT, " +
            TableMatches.COL_BARRACKS_DIRE + " INT, " +
            TableMatches.COL_CLUSTER + " MEDIUMINT, " +
            TableMatches.COL_FIRST_BLOOD_TIME + " BIGINT, " +
            TableMatches.COL_LOBBY_TYPE + " SMALLINT, " +
            TableMatches.COL_HUMAN_PLAYERS + " TINYINT(2), " +
            TableMatches.COL_GAME_MODE + " TINYINT(2) );" +
                    "" +
            "CREATE TABLE " + TableMatchPlayers.TABLE_NAME + " (" +
            TableMatchPlayers.COL_MATCH_ID + " INT, " +
            TableMatchPlayers.COL_ACCOUNT_ID + " BIGINT(11), " +
            TableMatchPlayers.COL_PLAYER_SLOT + " MEDIUMINT, " +
            TableMatchPlayers.COL_HERO_ID + " SMALLINT, " +
            TableMatchPlayers.COL_ITEMS + " VARCHAR(23), " +
            TableMatchPlayers.COL_KILLS + " TINYINT, " +
            TableMatchPlayers.COL_DEATHS + " TINYINT, " +
            TableMatchPlayers.COL_ASSISTS + " TINYINT, " +
            TableMatchPlayers.COL_GOLD + " MEDIUMINT, " +
            TableMatchPlayers.COL_LAST_HITS + " SMALLINT, " +
            TableMatchPlayers.COL_DENIES + " SMALLINT, " +
            TableMatchPlayers.COL_GPM + " SMALLINT, " +
            TableMatchPlayers.COL_XPM + " SMALLINT, " +
            TableMatchPlayers.COL_GOLD_SPENT + " MEDIUMINT, " +
            TableMatchPlayers.COL_HERO_DMG + " MEDIUMINT, " +
            TableMatchPlayers.COL_TOWER_DMG  + " MEDIUMINT, " +
            TableMatchPlayers.COL_HERO_HEAL + " MEDIUMINT, " +
            TableMatchPlayers.COL_LEVEL + " TINYINT(2), " +
            TableMatchPlayers.COL_UPGRADES + " TEXT )";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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

    public static abstract class TableMatchPlayers {
        public static final String TABLE_NAME = "match_players";

        public static final String COL_MATCH_ID    = "match_id";
        public static final String COL_ACCOUNT_ID  = "acount_id";
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
}
