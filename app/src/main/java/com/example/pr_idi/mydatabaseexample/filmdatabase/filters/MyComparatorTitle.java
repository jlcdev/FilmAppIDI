package com.example.pr_idi.mydatabaseexample.filmdatabase.filters;

import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

import java.util.Comparator;

/**
 * Created by Domin on 05/01/2017.
 */

public class MyComparatorTitle implements Comparator<Film>
{
        @Override
        public int compare(Film a, Film b)
        {
            return a.getTitle().toLowerCase().compareTo(b.getTitle().toLowerCase());
        }
}
