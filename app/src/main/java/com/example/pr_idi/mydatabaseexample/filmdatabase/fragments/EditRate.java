package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;


public class EditRate extends Fragment
{
    public static final int TAG = 4;
    private OnFragmentInteractionListener parentListener;
    private FilmData database;

    public EditRate(){}

    public static EditRate newInstance(Bundle bundle, FilmData filmData){
        EditRate editRate = new EditRate();
        editRate.database = filmData;
        editRate.setArguments(bundle);
        return editRate;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_edit_rate, container, false);
        Bundle bundle = this.getArguments();
        Film film = null;
        if(bundle != null){
            long filmId = bundle.getLong("id", -1);
            if(filmId != -1) film = database.getFilm(filmId);
        }
        final TextView textView = (TextView) view.findViewById(R.id.label_edit_rate_puntuation);
        Button button = (Button) view.findViewById(R.id.edit_rate_button_save);
        textView.setText("0");
        if(film != null){
            textView.setText(""+film.getCritics_rate());
            final Film finalFilm = film;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int num = Integer.parseInt(textView.getText().toString());
                    Film mFilm = new Film();
                    mFilm.setId(finalFilm.getId());
                    mFilm.setTitle(finalFilm.getTitle());
                    mFilm.setDirector(finalFilm.getDirector());
                    mFilm.setProtagonist(finalFilm.getProtagonist());
                    mFilm.setCountry(finalFilm.getCountry());
                    mFilm.setYear(finalFilm.getYear());
                    mFilm.setCritics_rate(num);
                    database.editFilm(mFilm);
                    //Go to film list screen
                    parentListener.onFragmentInteraction(ShowFilms.TAG, new Bundle());
                }
            });
        }
        ImageButton downButton = (ImageButton) view.findViewById(R.id.edit_rate_button_down);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(textView.getText().toString());
                num -= 1;
                if(num < 0) num = 0;
                textView.setText(""+num);
            }
        });
        ImageButton upButton = (ImageButton) view.findViewById(R.id.edit_rate_button_up);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(textView.getText().toString());
                num += 1;
                if(num > 10) num = 10;
                textView.setText(""+num);
            }
        });
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
