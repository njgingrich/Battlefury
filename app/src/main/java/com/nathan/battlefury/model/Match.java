package com.nathan.battlefury.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represent a completed match for a user.
 * <p/>
 * Created by nathan on 3/28/15.
 */
public class Match {
    private Player[] radiant;
    private Player[] dire;
    private boolean radiant_win;

    private int duration;
    private long start_time; // unix timestamp
    private long match_id;

    private int tower_status_radiant;
    private int tower_status_dire;
    private int barracks_status_radiant;
    private int barracks_status_dire;
    private int cluster;

    private int first_blood_time;
    private int lobby_type;
    private int human_players;
    private GameMode game_mode;

    public Player[] getRadiant() {
        return radiant;
    }

    public void setRadiant(Player[] radiant) {
        this.radiant = radiant;
    }

    public Player[] getDire() {
        return dire;
    }

    public void setDire(Player[] dire) {
        this.dire = dire;
    }

    public boolean isRadiant_win() {
        return radiant_win;
    }

    public void setRadiant_win(boolean radiant_win) {
        this.radiant_win = radiant_win;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(long match_id) {
        this.match_id = match_id;
    }

    public int getTower_status_radiant() {
        return tower_status_radiant;
    }

    public void setTower_status_radiant(int tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
    }

    public int getTower_status_dire() {
        return tower_status_dire;
    }

    public void setTower_status_dire(int tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
    }

    public int getBarracks_status_radiant() {
        return barracks_status_radiant;
    }

    public void setBarracks_status_radiant(int barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
    }

    public int getBarracks_status_dire() {
        return barracks_status_dire;
    }

    public void setBarracks_status_dire(int barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getFirst_blood_time() {
        return first_blood_time;
    }

    public void setFirst_blood_time(int first_blood_time) {
        this.first_blood_time = first_blood_time;
    }

    public int getLobby_type() {
        return lobby_type;
    }

    public void setLobby_type(int lobby_type) {
        this.lobby_type = lobby_type;
    }

    public int getHuman_players() {
        return human_players;
    }

    public void setHuman_players(int human_players) {
        this.human_players = human_players;
    }

    public GameMode getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(GameMode game_mode) {
        this.game_mode = game_mode;
    }
}
