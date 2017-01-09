package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;


public class EditRate extends Fragment implements View.OnClickListener
{
    public static final String TAG = "EditRate";
    private static final String KEY_SAVED_ID = "id";
    private OnFragmentInteractionListener parentListener;
    private FilmData database;
    private Film film;
    private TextView puntuation;
    private TextView title;

    public EditRate(){}

    public static EditRate newInstance(Bundle bundle, FilmData filmData)
    {
        EditRate editRate = new EditRate();
        editRate.database = filmData;
        editRate.setArguments(bundle);
        if(bundle != null){
            long filmId = bundle.getLong(KEY_SAVED_ID, -1L);
            editRate.film = filmData.getFilm(filmId);
        }
        return editRate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_edit_rate, container, false);
        view.findViewById(R.id.edit_rate_button_save).setOnClickListener(this);
        puntuation = (TextView) view.findViewById(R.id.edit_rate_field_puntuation);
        title = (TextView) view.findViewById(R.id.edit_rate_field_title);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.edit_rate_field_seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                puntuation.setText(""+progress);
                if(progress < 4) puntuation.setTextColor(ContextCompat.getColor(getActivity(), R.color.danger));
                else if(progress > 3 && progress < 7) puntuation.setTextColor(ContextCompat.getColor(getActivity(), R.color.accent));
                else puntuation.setTextColor(ContextCompat.getColor(getActivity(), R.color.success));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if(film != null){
            int num = film.getCritics_rate();
            puntuation.setText("" + num);
            title.setText(film.getTitle());
            seekBar.setProgress(num);
        }
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

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.edit_rate_button_save:
                if(film != null){
                    int num = Integer.parseInt(puntuation.getText().toString());
                    film.setCritics_rate(num);
                    database.updateFilm(film);
                    parentListener.onFragmentInteraction(ShowFilms.TAG, new Bundle());
                }
                break;
        }
    }
}
