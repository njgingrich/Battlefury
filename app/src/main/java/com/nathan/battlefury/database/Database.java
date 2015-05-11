package com.nathan.battlefury.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by nathan on 5/10/15.
 */
public class Database {
    private SQLiteDatabase db;
    private DatabaseHandler handler;

    public Database(Context context) {
        handler = new DatabaseHandler(context);
    }


}
