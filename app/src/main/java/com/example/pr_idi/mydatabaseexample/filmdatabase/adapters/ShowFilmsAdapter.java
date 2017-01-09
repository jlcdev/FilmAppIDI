package com.example.pr_idi.mydatabaseexample.filmdatabase.adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
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

public class ShowFilmsAdapter extends RecyclerView.Adapter<ShowFilmsAdapter.FilmViewHolder> implements View.OnClickListener
{
    private List<Film> filmList;
    private View.OnClickListener listener;

    public ShowFilmsAdapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public ShowFilmsAdapter.FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_show_films, parent, false);
        view.setOnClickListener(this);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowFilmsAdapter.FilmViewHolder holder, int pos)
    {
        int critical = filmList.get(pos).getCritics_rate();
        holder.id = filmList.get(pos).getId();
        holder.title.setText(filmList.get(pos).getTitle() + " (" + filmList.get(pos).getYear() + ")");
        holder.director.setText(filmList.get(pos).getDirector());
        holder.actor.setText(filmList.get(pos).getProtagonist());
        holder.country.setText(filmList.get(pos).getCountry());
        holder.rate.setText("" + critical);
        switch(critical)
        {
            case 0: holder.rate.setBackgroundColor(Color.rgb(255, 0, 0)); break;
            case 1: holder.rate.setBackgroundColor(Color.rgb(225, 25, 5)); break;
            case 2: holder.rate.setBackgroundColor(Color.rgb(200, 50, 5)); break;
            case 3: holder.rate.setBackgroundColor(Color.rgb(175, 75, 5)); break;
            case 4: holder.rate.setBackgroundColor(Color.rgb(150, 100, 5)); break;
            case 5: holder.rate.setBackgroundColor(Color.rgb(125, 125, 5)); break;
            case 6: holder.rate.setBackgroundColor(Color.rgb(100, 150, 5)); break;
            case 7: holder.rate.setBackgroundColor(Color.rgb(75, 175, 5)); break;
            case 8: holder.rate.setBackgroundColor(Color.rgb(50, 200, 5)); break;
            case 9: holder.rate.setBackgroundColor(Color.rgb(25, 225, 5)); break;
            case 10: holder.rate.setBackgroundColor(Color.rgb(0, 255, 0)); break;
        }
    }

    @Override
    public int getItemCount()
    {
        return filmList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View view)
    {
        if(listener != null) listener.onClick(view);
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder
    {
        Long id;
        CardView cardView;
        TextView title;
        TextView director;
        TextView actor;
        TextView country;
        TextView rate;

        FilmViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.list_item_show_films_cardview);
            title = (TextView)itemView.findViewById(R.id.show_films_field_title);
            director = (TextView)itemView.findViewById(R.id.show_films_field_director);
            actor = (TextView)itemView.findViewById(R.id.show_films_field_actor);
            country = (TextView)itemView.findViewById(R.id.show_films_field_country);
            rate = (TextView)itemView.findViewById(R.id.show_films_field_rate);
        }

        public Long getId()
        {
            return id;
        }
    }
}
