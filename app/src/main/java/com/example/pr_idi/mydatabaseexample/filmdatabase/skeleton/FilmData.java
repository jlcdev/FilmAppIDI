package com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton;

/**
 * FilmData
 * Created by pr_idi on 10/11/16.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FilmData {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    // Here we only select Title and Director, must select the appropriate columns
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
                                    MySQLiteHelper.COLUMN_TITLE,
                                    MySQLiteHelper.COLUMN_DIRECTOR,
                                    MySQLiteHelper.COLUMN_PROTAGONIST,
                                    MySQLiteHelper.COLUMN_COUNTRY,
                                    MySQLiteHelper.COLUMN_YEAR_RELEASE,
                                    MySQLiteHelper.COLUMN_CRITICS_RATE};

    public FilmData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Film createFilm(String title, String director, String actor, String country, int year, int rate) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_DIRECTOR, director);
        values.put(MySQLiteHelper.COLUMN_COUNTRY, country);
        values.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, year);
        values.put(MySQLiteHelper.COLUMN_PROTAGONIST, actor);
        values.put(MySQLiteHelper.COLUMN_CRITICS_RATE, rate);

        long insertId = database.insert(MySQLiteHelper.TABLE_FILMS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Film newFilm = cursorToFilm(cursor);
        cursor.close();
        return newFilm;
    }

    public void deleteFilm(Film film)
    {
        database.delete(MySQLiteHelper.TABLE_FILMS, MySQLiteHelper.COLUMN_ID + " = " + film.getId(), null);
    }

    public List<Film> getAllFilms()
    {
        List<Film> comments = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Film comment = cursorToFilm(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    public Film getFilm(long id){
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Film newFilm = cursorToFilm(cursor);
        cursor.close();
        return newFilm;
    }

    public Film updateFilm(Film film){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE, film.getTitle());
        values.put(MySQLiteHelper.COLUMN_DIRECTOR, film.getDirector());
        values.put(MySQLiteHelper.COLUMN_PROTAGONIST, film.getProtagonist());
        values.put(MySQLiteHelper.COLUMN_COUNTRY, film.getCountry());
        values.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, film.getYear());
        values.put(MySQLiteHelper.COLUMN_CRITICS_RATE, film.getCritics_rate());

        database.update(MySQLiteHelper.TABLE_FILMS, values, MySQLiteHelper.COLUMN_ID + " = " + film.getId(), null);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + film.getId(), null, null, null, null);
        cursor.moveToFirst();
        Film newFilm = cursorToFilm(cursor);
        cursor.close();
        return newFilm;
    }

    private Film cursorToFilm(Cursor cursor)
    {
        Film film = new Film();
        film.setId(cursor.getLong(0));
        film.setTitle(cursor.getString(1));
        film.setDirector(cursor.getString(2));
        film.setProtagonist(cursor.getString(3));
        film.setCountry(cursor.getString(4));
        film.setYear(cursor.getInt(5));
        film.setCritics_rate(cursor.getInt(6));
        return film;
    }
}