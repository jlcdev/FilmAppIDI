package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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


public class EditRate extends Fragment implements View.OnClickListener
{
    public static final String TAG = "EditRate";
    private static final String KEY_SAVED_ID = "id";
    private OnFragmentInteractionListener parentListener;
    private FilmData database;
    private Film film;
    private TextView textView;

    public EditRate(){}

    public static EditRate newInstance(Bundle bundle, FilmData filmData)
    {
        EditRate editRate = new EditRate();
        editRate.database = filmData;
        editRate.setArguments(bundle);
        return editRate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_edit_rate, container, false);
        textView = (TextView) view.findViewById(R.id.label_edit_rate_puntuation);
        view.findViewById(R.id.edit_rate_button_save).setOnClickListener(this);
        view.findViewById(R.id.edit_rate_button_down).setOnClickListener(this);
        view.findViewById(R.id.edit_rate_button_up).setOnClickListener(this);

        if(savedInstanceState != null){
            //Restore
            Log.e("ERROR","STATE RESTORED");

            findFilm(savedInstanceState.getLong(KEY_SAVED_ID, -1));
        }else{
            //New Instance
            Log.e("ERROR","NORMAL ENTRY");
            Bundle bundle = getArguments();
            findFilm(bundle.getLong(KEY_SAVED_ID, -1));
        }
        return view;
    }

    private void findFilm(Long id){
        textView.setText("0");
        if(id != null && id != -1) film = database.getFilm(id);
        if(film != null) textView.setText(""+film.getCritics_rate());
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_SAVED_ID, getArguments().getLong(KEY_SAVED_ID, -1));
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
        int num = Integer.parseInt(textView.getText().toString());
        switch(v.getId())
        {
            case R.id.edit_rate_button_up:
                num += 1;
                break;
            case R.id.edit_rate_button_down:
                num -= 1;
                break;
            case R.id.edit_rate_button_save:
                if(film != null){
                    film.setCritics_rate(num);
                    database.editFilm(film);
                    parentListener.onFragmentInteraction(ShowFilms.TAG, new Bundle());
                }
                break;
        }
        if(num > 10) num = 10;
        if(num < 0) num = 0;
        textView.setText(""+num);
    }
}
