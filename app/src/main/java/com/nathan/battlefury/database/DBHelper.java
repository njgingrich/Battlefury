package com.nathan.battlefury.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nathan.battlefury.model.Match;

import java.util.LinkedList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by nathan on 5/10/15.
 */
public class DBHelper {
    private SQLiteDatabase db;
    private SQLHelper handler;

    public DBHelper(Context context) {
        handler = new SQLHelper(context);
    }

    public long insertMatch(Match m) {
        return cupboard().withDatabase(db).put(m);
    }

    public List<Match> getAllMatches() {
        QueryResultIterable<Match> itr = null;
        return cupboard().withDatabase(db).query(Match.class).list();
    }
}
