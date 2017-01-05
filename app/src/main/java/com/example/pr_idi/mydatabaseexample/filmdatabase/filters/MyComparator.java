package com.example.pr_idi.mydatabaseexample.filmdatabase.filters;

import java.util.Comparator;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

/**
 * Created by Domin on 05/01/2017.
 */

public class MyComparator implements Comparator<Film>
{
        @Override
        public int compare(Film a, Film b)
        {
            return a.getProtagonist().compareTo(b.getProtagonist());
        }
}
