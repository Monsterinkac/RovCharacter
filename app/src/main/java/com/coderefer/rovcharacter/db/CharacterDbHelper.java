package com.coderefer.rovcharacter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 7/12/2560.
 */

public class CharacterDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "character.db";
    private static final int DATABASE_VERSION = 8;

    public static final String TABLE_NAME = "charater_game";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_POSITIONX = "position";
    public static final String COL_PICTURE = "charater_img";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            + COL_POSITIONX + " TEXT, "
            + COL_PICTURE + " TEXT)";

    public CharacterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);
    }
    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "TAARA");
        cv.put(COL_POSITIONX, "TANK");
        cv.put(COL_PICTURE, "taara.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "BUTTERFLY");
        cv.put(COL_POSITIONX, "ASSASSIN");
        cv.put(COL_PICTURE, "butterfly.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "LUBU");
        cv.put(COL_POSITIONX, "FIGHTER");
        cv.put(COL_PICTURE, "lubu.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "YORN");
        cv.put(COL_POSITIONX, "CARRY");
        cv.put(COL_PICTURE, "yorn.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "PAYNA");
        cv.put(COL_POSITIONX, "SUPPORT");
        cv.put(COL_PICTURE, "payna.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "VEERA");
        cv.put(COL_POSITIONX, "MAGE");
        cv.put(COL_PICTURE, "veera.jpg");
        db.insert(TABLE_NAME, null, cv);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
