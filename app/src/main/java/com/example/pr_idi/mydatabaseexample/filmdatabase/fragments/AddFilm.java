package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class AddFilm extends Fragment implements View.OnClickListener
{
    public static final int TAG = 3;
    public static int LIMIT_FILM_YEAR = 1885;
    private OnFragmentInteractionListener parentListener;
    private EditText title;
    private EditText director;
    private EditText actor;
    private Spinner spinnerYear;
    private Spinner spinnerCountry;
    private TextView puntuation;
    private int actualYear;
    private FilmData database;


    public AddFilm(){}

    public static AddFilm newInstance(Bundle bundle, FilmData filmData){
        AddFilm addFilm = new AddFilm();
        addFilm.database = filmData;
        return addFilm;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_film, container, false);
        spinnerYear = (Spinner) view.findViewById(R.id.add_film_field_year);
        spinnerCountry = (Spinner) view.findViewById(R.id.add_film_field_country);
        title = (EditText) view.findViewById(R.id.add_film_field_title);
        director = (EditText) view.findViewById(R.id.add_film_field_director);
        actor = (EditText) view.findViewById(R.id.add_film_field_actor);
        puntuation = (TextView) view.findViewById(R.id.add_film_field_puntuation);
        ImageButton downButton = (ImageButton) view.findViewById(R.id.add_film_field_down);
        ImageButton upButton = (ImageButton) view.findViewById(R.id.add_film_field_up);
        downButton.setOnClickListener(this);
        upButton.setOnClickListener(this);


        //Set spinner for year
        Calendar calendar = Calendar.getInstance();
        actualYear = calendar.get(Calendar.YEAR);
        int limit = actualYear - LIMIT_FILM_YEAR;
        String[] selectYears = new String[limit];
        for(int i = 0; i < limit; ++i){
            selectYears[i] = ""+(actualYear-i);
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, selectYears);
        spinnerYear.setAdapter(yearAdapter);

        //Set spinner for countries
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length()>0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        String[] selectCountries = new String[countries.size()];
        for(int i = 0; i < countries.size(); ++i){
            selectCountries[i] = countries.get(i);
        }

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, selectCountries);
        spinnerCountry.setAdapter(countryAdapter);

        Button saveButton = (Button) view.findViewById(R.id.add_film_button_save);
        saveButton.setOnClickListener(this);
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

    private void storeFilm(){
        boolean warning = false;
        String title = this.title.getText().toString();
        if(title.isEmpty()){
            warning = true;
            this.title.setError("No pot ser buit");
        }else this.title.setError(null);
        String director = this.director.getText().toString();
        if(director.isEmpty()){
            warning = true;
            this.director.setError("No pot ser buit");
        }else this.director.setError(null);
        String actor = this.actor.getText().toString();
        if(actor.isEmpty()){
            warning = true;
            this.actor.setError("No pot ser buit");
        }else this.actor.setError(null);
        int year = Integer.parseInt(this.spinnerYear.getSelectedItem().toString());
        String country = this.spinnerCountry.getSelectedItem().toString();
        int rate = Integer.parseInt(this.puntuation.getText().toString());
        if(rate < 0 || rate > 10){
            warning = true;
            this.puntuation.setError("Entre 0 i 10");
        }else this.puntuation.setError(null);
        if(!warning){
            database.createFilm(title, director, actor, country, year, rate);
            parentListener.onFragmentInteraction(SearchByTitle.TAG, new Bundle());
        }
    }

    @Override
    public void onClick(View v)
    {
        int num;
        if(this.title == null || this.director == null || this.actor == null || this.spinnerYear == null || this.spinnerCountry == null) return;
        switch(v.getId()){
            case R.id.add_film_button_save:
                storeFilm();
                break;

            case R.id.add_film_field_down:
                num = Integer.parseInt(this.puntuation.getText().toString());
                num -= 1;
                if(num < 0) num = 0;
                this.puntuation.setText(""+num);
                break;

            case R.id.add_film_field_up:
                num = Integer.parseInt(this.puntuation.getText().toString());
                num += 1;
                if(num > 10) num = 10;
                this.puntuation.setText(""+num);
                break;
        }
    }
}
