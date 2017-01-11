package com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton;

/**
 * MySQLiteHelper
 * Created by pr_idi on 10/11/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_FILMS = "films";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_YEAR_RELEASE = "year_release";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_PROTAGONIST = "protagonist";
    public static final String COLUMN_CRITICS_RATE = "critics_rate";

    private static final String DATABASE_NAME = "films.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_FILMS + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_COUNTRY + " text not null, "
            + COLUMN_YEAR_RELEASE + " integer not null, "
            + COLUMN_DIRECTOR + " text not null, "
            + COLUMN_PROTAGONIST + " text not null, "
            + COLUMN_CRITICS_RATE + " integer"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);

        ContentValues film1 = new ContentValues();
        film1.put(MySQLiteHelper.COLUMN_TITLE, "Iron Man");
        film1.put(MySQLiteHelper.COLUMN_DIRECTOR, "Jon Favreau");
        film1.put(MySQLiteHelper.COLUMN_COUNTRY, "United States");
        film1.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, "2008");
        film1.put(MySQLiteHelper.COLUMN_PROTAGONIST, "Robert Downey Jr.");
        film1.put(MySQLiteHelper.COLUMN_CRITICS_RATE, "10");
        database.insert(MySQLiteHelper.TABLE_FILMS, null, film1);

        ContentValues film2 = new ContentValues();
        film2.put(MySQLiteHelper.COLUMN_TITLE, "El Padrino");
        film2.put(MySQLiteHelper.COLUMN_DIRECTOR, "Francis Ford Coppola");
        film2.put(MySQLiteHelper.COLUMN_COUNTRY, "United States");
        film2.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, "1972");
        film2.put(MySQLiteHelper.COLUMN_PROTAGONIST, "Marlon Brando");
        film2.put(MySQLiteHelper.COLUMN_CRITICS_RATE, "9");
        database.insert(MySQLiteHelper.TABLE_FILMS, null, film2);

        ContentValues film3 = new ContentValues();
        film3.put(MySQLiteHelper.COLUMN_TITLE, "Sherlock Holmes");
        film3.put(MySQLiteHelper.COLUMN_DIRECTOR, "Guy Ritchie");
        film3.put(MySQLiteHelper.COLUMN_COUNTRY, "United States");
        film3.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, "2009");
        film3.put(MySQLiteHelper.COLUMN_PROTAGONIST, "Robert Downey Jr.");
        film3.put(MySQLiteHelper.COLUMN_CRITICS_RATE, "8");
        database.insert(MySQLiteHelper.TABLE_FILMS, null, film3);

        ContentValues film4 = new ContentValues();
        film4.put(MySQLiteHelper.COLUMN_TITLE, "El Caballero Oscuro");
        film4.put(MySQLiteHelper.COLUMN_DIRECTOR, "Christopher Nolan");
        film4.put(MySQLiteHelper.COLUMN_COUNTRY, "United States");
        film4.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, "2009");
        film4.put(MySQLiteHelper.COLUMN_PROTAGONIST, "Christian Bale");
        film4.put(MySQLiteHelper.COLUMN_CRITICS_RATE, "8");
        database.insert(MySQLiteHelper.TABLE_FILMS, null, film4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMS);
        onCreate(db);
    }

}
