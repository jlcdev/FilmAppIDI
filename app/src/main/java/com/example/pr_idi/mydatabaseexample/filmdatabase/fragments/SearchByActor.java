package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.adapters.SearchActorAdapter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.filters.MyComparatorActor;
import com.example.pr_idi.mydatabaseexample.filmdatabase.filters.ActorFilter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;

import java.util.Collections;
import java.util.List;

public class SearchByActor extends Fragment
{
    public static final String TAG = "SearchByActor";
    private OnFragmentInteractionListener parentListener;
    private ListView listView;
    private AutoCompleteTextView autoCompleteTextView;
    private FilmData database;
    private TextWatcher searchFieldWatcher;
    private SearchActorAdapter searchActorAdapter;
    private List<Film> films;

    public SearchByActor(){}

    public static SearchByActor newInstance(Bundle bundle, FilmData filmData){
        SearchByActor searchByActor = new SearchByActor();
        searchByActor.database = filmData;
        return searchByActor;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_by_actor, container, false);
        listView = (ListView) view.findViewById(R.id.list_search_actor);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.field_search_actor);
        films = database.getAllFilms();
        Collections.sort(films,new MyComparatorActor());

        //Set autocomplete values
        String[] proposals = new String[films.size()];
        for(int i=0; i < films.size(); ++i){
            proposals[i] = films.get(i).getProtagonist();
        }
        ArrayAdapter<String> proposalAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, proposals);
        autoCompleteTextView.setAdapter(proposalAdapter);

        //Set list values
        searchActorAdapter = new SearchActorAdapter(this, view.getContext(), films);
        listView.setAdapter(searchActorAdapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Film film = (Film) searchActorAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("actor", film.getProtagonist());
                parentListener.onFragmentInteraction(ShowFilms.TAG, bundle);
            }
        });

        //filtering list values with autocomplete field information
        searchFieldWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                ActorFilter filter = searchActorAdapter.getFilter();
                filter.setOriginalFilmList(films);
                filter.filter(s);
            }
            @Override
            public void afterTextChanged(Editable s){}
        };
        autoCompleteTextView.addTextChangedListener(searchFieldWatcher);
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
