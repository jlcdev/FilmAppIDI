package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.adapters.SearchFilmAdapter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;

import java.util.List;

public class SearchByTitle extends Fragment
{
    public static final int TAG = 0;
    private OnFragmentInteractionListener parentListener;
    private ListView listView;
    private AutoCompleteTextView autoCompleteTextView;
    private FilmData database;

    public SearchByTitle(){}

    public static SearchByTitle newInstance(Bundle bundle, FilmData filmData){
        SearchByTitle searchByTitle = new SearchByTitle();
        searchByTitle.database = filmData;
        return searchByTitle;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_by_title, container, false);
        listView = (ListView) view.findViewById(R.id.list_search_title);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.field_search_title);
        List<Film> values = database.getAllFilms();
        //Set autocomplete values
        String[] proposals = new String[values.size()];
        for(int i=0; i < values.size(); ++i){
            proposals[i] = values.get(i).getTitle();
        }
        ArrayAdapter<String> proposalAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, proposals);
        autoCompleteTextView.setAdapter(proposalAdapter);
        SearchFilmAdapter searchFilmAdapter = new SearchFilmAdapter(view.getContext(), values);
        listView.setAdapter(searchFilmAdapter);
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
