package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.pr_idi.mydatabaseexample.filmdatabase.adapters.SearchFilmAdapter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.filters.FilmFilter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.filters.MyComparatorTitle;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchByTitle extends Fragment
{
    public static final String TAG = "SearchByTitle";
    private OnFragmentInteractionListener parentListener;
    private ListView listView;
    private View view;
    private AutoCompleteTextView autoCompleteTextView;
    private FilmData database;
    private TextWatcher searchFieldWatcher;
    private SearchFilmAdapter searchFilmAdapter;
    private List<Film> films;

    public SearchByTitle(){}

    public static SearchByTitle newInstance(Bundle bundle, FilmData filmData){
        SearchByTitle searchByTitle = new SearchByTitle();
        searchByTitle.database = filmData;
        return searchByTitle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_by_title, container, false);
        listView = (ListView) view.findViewById(R.id.list_search_title);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.field_search_title);
        films = new ArrayList<>();
        if(database != null){
            films = database.getAllFilms();
        }
        Collections.sort(films, new MyComparatorTitle());

        //Set autocomplete values
        String[] proposals = new String[films.size()];
        for(int i=0; i < films.size(); ++i){
            proposals[i] = films.get(i).getTitle();
        }
        ArrayAdapter<String> proposalAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, proposals);
        autoCompleteTextView.setAdapter(proposalAdapter);

        //Set list values
        searchFilmAdapter = new SearchFilmAdapter(this, view.getContext(), films);
        listView.setAdapter(searchFilmAdapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Film film = (Film) searchFilmAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putLong("id", film.getId());
                parentListener.onFragmentInteraction(ShowFilms.TAG, bundle);
            }
        });

        //filtering list values with autocomplete field information
        searchFieldWatcher = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                FilmFilter filter = searchFilmAdapter.getFilter();
                filter.setOriginalFilmList(films);
                filter.filter(s);
                //
                //searchFilmAdapter.notifyDataSetChanged();
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

    public void delete(final int position){
        //final Film film = this.films.get(position);
        final Film film = (Film) searchFilmAdapter.getItem(position);
        int realPos = -1;

        //Encontrar la posicion real en la lista
        for(int i = 0; i<films.size(); i++)
        {
            if (film.equals(films.get(i)))
            {
                realPos = i;
                break;
            }
        }
        final int realPos2 = realPos;
        AlertDialog.Builder adb = new AlertDialog.Builder(this.getContext());
        adb.setTitle("Esborrar?");
        adb.setMessage("Estàs segur d'el·liminar la pel·lícula "+ film.getTitle());
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                database.deleteFilm(film);
                films.remove(realPos2);
                searchFilmAdapter.updateAdapter(films);
                searchFilmAdapter.notifyDataSetChanged();
                //Vaciamos la caja de búsqueda
                autoCompleteTextView.setText("");
            }
        });
        adb.show();
    }
}
