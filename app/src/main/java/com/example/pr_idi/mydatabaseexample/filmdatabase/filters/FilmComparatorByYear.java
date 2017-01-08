package com.example.pr_idi.mydatabaseexample.filmdatabase.filters;

import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

import java.util.Comparator;

/**
 * Created by javierlopezcalderon on 8/1/17.
 */

public class FilmComparatorByYear implements Comparator<Film>
{
    @Override
    public int compare(Film a, Film b)
    {
        return b.getYear() - a.getYear();
    }
}
