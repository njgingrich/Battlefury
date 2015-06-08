package com.nathan.battlefury.rest.event;

import com.nathan.battlefury.model.MatchHistory;

/**
 * Created by nathan on 6/3/15.
 */
public class MatchesLoadedEvent {
    private MatchHistory history;

    public MatchesLoadedEvent(MatchHistory history) {
        this.history = history;
    }

    public MatchHistory get() {
        return history;
    }
}
