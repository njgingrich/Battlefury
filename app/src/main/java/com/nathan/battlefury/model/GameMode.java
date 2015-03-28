package com.nathan.battlefury.model;

/**
 * Created by nathan on 3/28/15.
 */
public enum GameMode {
    NONE("None"),
    ALL_PICK("All Pick"),
    CAPTAINS_MODE("Captain's Mode"),
    RANDOM_DRAFT("Random Draft"),
    SINGLE_DRAFT("Single Draft"),
    ALL_RANDOM("All Random"),
    INTRO("Intro"),
    DIRETIDE("Diretide"),
    R_CAPTAINS_MODE("Reverse Captain's Mode"),
    GREEVILING("The Greeviling"),
    TUTORIAL("Tutorial"),
    MID_ONLY("Mid Only"),
    LEAST_PLAYED("Least Played"),
    NEW_PLAYER("New Player Pool"),
    COMPENDIUM("Compendium Matchmaking"),
    CAPTAINS_DRAFT("Captains Draft");

    private String name;

    private GameMode(String str) {
        name = str;
    }

    @Override
    public String toString() {
        return name;
    }
}
