package com.nathan.battlefury.model;

/**
 * Wrapper class for a player's ability upgrades in a match.
 * <p/>
 * Created by nathan on 3/28/15.
 */
public class AbilityUpgrade {
    private int ability;
    private int time;
    private int level;

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
