package com.example.pr_idi.mydatabaseexample.filmdatabase.filters;

import android.widget.Filter;

import com.example.pr_idi.mydatabaseexample.filmdatabase.adapters.SearchActorAdapter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierlopezcalderon on 4/1/17.
 */

public class ActorFilter extends Filter
{
    private List<Film> originalFilmList;
    private SearchActorAdapter searchActorAdapter;

    public ActorFilter(SearchActorAdapter searchFilmAdapter){
        this.searchActorAdapter = searchFilmAdapter;
    }

    public void setOriginalFilmList(List<Film> originalFilmList){
        this.originalFilmList = originalFilmList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length() > 0){
            List<Film> filterList = new ArrayList<>();
            int size = this.originalFilmList.size();
            for(int i = 0; i < size; ++i){
                if(this.originalFilmList.get(i).getProtagonist().toLowerCase().contains(constraint.toString().toLowerCase())){
                    filterList.add(this.originalFilmList.get(i));
                }
            }

            results.count=filterList.size();
            results.values=filterList;
        }
        else{
            results.count = this.originalFilmList.size();
            results.values = this.originalFilmList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results)
    {
        this.searchActorAdapter.updateAdapter((List<Film>)results.values);
    }
}
