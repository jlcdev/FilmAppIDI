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
import com.example.pr_idi.mydatabaseexample.filmdatabase.filters.FilmComparatorByYear;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShowFilms extends Fragment
{
    public static final String TAG = "ShowFilms";
    private OnFragmentInteractionListener parentListener;
    private RecyclerView recyclerView;
    private List<Film> listFilm;
    private List<Film> filteredList;
    private String actor;
    private long id;

    public ShowFilms(){}

    public static ShowFilms newInstance(Bundle bundle, FilmData filmData){
        ShowFilms showFilms = new ShowFilms();
        showFilms.setArguments(bundle);
        showFilms.listFilm = filmData.getAllFilms();
        showFilms.filteredList = new ArrayList<>();
        if(bundle != null){
            showFilms.id = bundle.getLong("id", -1L);
            showFilms.actor = bundle.getString("actor", null);
        }
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

        //Filter data
        filteredList = new ArrayList<>();
        filteredList = filterById(id, listFilm);
        filteredList = filterByActor(actor, filteredList);
        Collections.sort(filteredList, new FilmComparatorByYear());
        ShowFilmsAdapter showFilmsAdapter = new ShowFilmsAdapter(filteredList);
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

    private List<Film> cloneListFilm(List<Film> original){
        List<Film> response = new ArrayList<>();
        if(original == null) return response;
        for(Film f : original){
            Film x = new Film();
            x.setId(f.getId());
            x.setTitle(f.getTitle());
            x.setProtagonist(f.getProtagonist());
            x.setCountry(f.getCountry());
            x.setYear(f.getYear());
            x.setCritics_rate(f.getCritics_rate());
            x.setDirector(f.getDirector());
            response.add(x);
        }
        return response;
    }

    private List<Film> filterById(long id, List<Film> filmList)
    {
        List<Film> cloned = cloneListFilm(filmList);
        if(id == -1L) return cloned;
        List<Film> deleteCandidates = new ArrayList<>();
        for(Film f : cloned){
            if(f.getId() != id) deleteCandidates.add(f);
        }
        for(Film f : deleteCandidates){
            cloned.remove(f);
        }
        return cloned;
    }

    private List<Film> filterByActor(String actor, List<Film> filmList)
    {
        List<Film> cloned = cloneListFilm(filmList);
        if(actor == null || actor.isEmpty()) return cloned;
        List<Film> deleteCandidates = new ArrayList<>();
        for(Film f : cloned){
            if(!f.getProtagonist().equalsIgnoreCase(actor)) deleteCandidates.add(f);
        }
        for(Film f : deleteCandidates){
            cloned.remove(f);
        }
        return cloned;
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
