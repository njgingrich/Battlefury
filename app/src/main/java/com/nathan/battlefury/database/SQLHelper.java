package com.nathan.battlefury.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nathan.battlefury.model.AbilityUpgrade;
import com.nathan.battlefury.model.Match;
import com.nathan.battlefury.model.Player;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by nathan on 3/29/15.
 */
public class SQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "matches.db";

    static {
        // register our models
        cupboard().register(Player.class);
        cupboard().register(Match.class);
        cupboard().register(AbilityUpgrade.class);
    }

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
