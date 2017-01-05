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
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;

public class ShowFilms extends Fragment
{
    public static final int TAG = 2;
    private OnFragmentInteractionListener parentListener;
    private FilmData database;

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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.show_films_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
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
