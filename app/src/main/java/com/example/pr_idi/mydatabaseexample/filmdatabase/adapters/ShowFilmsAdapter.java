package com.example.pr_idi.mydatabaseexample.filmdatabase.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

import java.util.List;

/**
 * Created by javierlopezcalderon on 5/1/17.
 */

public class ShowFilmsAdapter extends RecyclerView.Adapter<ShowFilmsAdapter.FilmViewHolder>
{
    private List<Film> filmList;

    public ShowFilmsAdapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public ShowFilmsAdapter.FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_show_films, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowFilmsAdapter.FilmViewHolder holder, int pos)
    {
        holder.title.setText(filmList.get(pos).getTitle());
        holder.director.setText(filmList.get(pos).getDirector());
        holder.actor.setText(filmList.get(pos).getProtagonist());
        holder.country.setText(filmList.get(pos).getCountry());
        holder.year.setText("" + filmList.get(pos).getYear());
        holder.rate.setText("" + filmList.get(pos).getCritics_rate());
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView director;
        TextView actor;
        TextView country;
        TextView year;
        TextView rate;

        FilmViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.show_films_field_title);
            director = (TextView)itemView.findViewById(R.id.show_films_field_director);
            actor = (TextView)itemView.findViewById(R.id.show_films_field_actor);
            country = (TextView)itemView.findViewById(R.id.show_films_field_country);
            year = (TextView)itemView.findViewById(R.id.show_films_field_year);
            rate = (TextView)itemView.findViewById(R.id.show_films_field_rate);
        }
    }
}
