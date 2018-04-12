package com.example.aggel.mypatternlock;

/**
 * Created by aggel on 14-Feb-18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper { //boithitiki klasi gia tin diaxirisi tis topikis vasis

    public DatabaseHelper(Context context)
    {
        super(context, "myPatterns.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table patterns (id integer primary key autoincrement , pattern text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insertPattern(String pattern)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pattern", pattern);

        db.insert("patterns",null, contentValues );
        Log.d("database","Pattern inserted: " + pattern);
    }

    public Cursor getPatterns() throws SQLiteException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery( "select pattern from patterns ", null );
        return result;
    }

    public void resetDB()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("drop table patterns");
        db.execSQL("create table patterns (id integer primary key autoincrement , pattern text)");
    }

}
