package com.nathan.battlefury.model;

import nl.qbusict.cupboard.annotation.Ignore;

/**
 * Represent a player within a match.
 * <p/>
 * Created by nathan on 3/28/15.
 */
public class Player {
    public long _id; // Steam account ID
    public int player_slot;
    public int hero_id;
    public String items; // Stored as item0,item1,item2,item3,item4,item5

    public int kills;
    public int deaths;
    public int assists;

    public int gold;
    public int last_hits;
    public int denies;

    public int gpm;
    public int xpm;
    public int gold_spent;

    public int hero_damage;
    public int tower_damage;
    public int hero_healing;
    public int level;
    @Ignore
    public AbilityUpgrade[] upgrades;
}
