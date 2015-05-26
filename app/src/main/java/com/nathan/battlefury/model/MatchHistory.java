package com.nathan.battlefury.model;

import java.util.LinkedList;

/**
 * Created by nathan on 5/26/15.
 */
public class MatchHistory {
    public int total_matches;
    public LinkedList<Long> matches;

    public MatchHistory() {
        matches = new LinkedList<>();
    }
}
