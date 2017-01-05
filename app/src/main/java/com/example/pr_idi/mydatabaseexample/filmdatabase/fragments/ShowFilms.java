package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.adapters.ShowFilmsAdapter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;

import java.util.List;

public class ShowFilms extends Fragment
{
    public static final int TAG = 2;
    private OnFragmentInteractionListener parentListener;
    private FilmData database;
    private RecyclerView recyclerView;

    public ShowFilms(){}

    public static ShowFilms newInstance(Bundle bundle, FilmData filmData){
        ShowFilms showFilms = new ShowFilms();
        showFilms.database = filmData;
        showFilms.setArguments(bundle);
        return showFilms;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_show_films, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.show_films_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        List<Film> listFilm = database.getAllFilms();
        //Filter options
        Bundle bundle = this.getArguments();
        if(bundle != null){
            String actor = bundle.getString("actor", null);
            long id = bundle.getLong("id", -1);
            for(int i = 0; i < listFilm.size();++i){
                Film film = listFilm.get(i);
                if(actor != null && !actor.isEmpty()){
                    if(!film.getProtagonist().equalsIgnoreCase(actor)) listFilm.remove(i);
                }
                if(id != -1){
                    if(film.getId() != id) listFilm.remove(i);
                }
            }

        }
        ShowFilmsAdapter showFilmsAdapter = new ShowFilmsAdapter(listFilm);
        showFilmsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFilmsAdapter.FilmViewHolder fvh = (ShowFilmsAdapter.FilmViewHolder) recyclerView.getChildViewHolder(v);
                Bundle bundle = new Bundle();
                bundle.putLong("id", fvh.getId());
                parentListener.onFragmentInteraction(EditRate.TAG, bundle);
            }
        });
        recyclerView.setAdapter(showFilmsAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener) parentListener = (OnFragmentInteractionListener) context;
        else throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        parentListener = null;

    }
}
