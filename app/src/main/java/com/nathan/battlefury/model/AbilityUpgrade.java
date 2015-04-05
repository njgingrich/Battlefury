package com.nathan.battlefury.model;

/**
 * Wrapper class for a player's ability upgrades in a match.
 * <p/>
 * Created by nathan on 3/28/15.
 */
public class AbilityUpgrade {
    public int ability;
    public int time;
    public int level;

    public AbilityUpgrade() {
        ability = -1;
        time = -1;
        level = -1;
    }

    @Override
    public String toString() {
        return "" + ability + "," + time + "," + level;
    }
}
