package com.nathan.battlefury.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represent a completed match for a user.
 * <p/>
 * Created by nathan on 3/28/15.
 */
public class Match {
    public long _id; // Steam match id
    public long player1;
    public long player2;
    public long player3;
    public long player4;
    public long player5;
    public long player6;
    public long player7;
    public long player8;
    public long player9;
    public long player10;
    public boolean radiant_win;

    public int duration;
    public long start_time; // unix timestamp
    public long match_id;

    public int tower_status_radiant;
    public int tower_status_dire;
    public int barracks_status_radiant;
    public int barracks_status_dire;
    public int cluster;

    public int first_blood_time;
    public int lobby_type;
    public int human_players;
    public int game_mode;
}
