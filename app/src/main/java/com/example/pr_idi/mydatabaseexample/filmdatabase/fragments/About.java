package com.example.pr_idi.mydatabaseexample.filmdatabase.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;


public class About extends Fragment {

    public static final String TAG = "About";
    private OnFragmentInteractionListener mListener;

    public About() {
        // Required empty public constructor
    }

    public static About newInstance() {
        About fragment = new About();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        TextView texto1 = (TextView) view.findViewById(R.id.textView6);
        TextView texto2 = (TextView) view.findViewById(R.id.textView7);
        TextView texto3 = (TextView) view.findViewById(R.id.textView8);
        texto1.setText("Aplicación realizada como entrega evaluable durante el curso 2016-2017, cuatrimestre de otoño para la asignatura de IDI.\n" +
                        "\n" +
                        "Desarrolladores de la interfaz\n");

        texto2.setText("Domingo Jesús de la Mata García\n" +
                        "Javier López Calderón\n");
        texto3.setText("\n" +
                        "Interacción y diseño de interfaces 2016-2017\n" +
                        "Facultad de Informática de Barcelona\n" +
                        "Universidad Politécnica de Cataluña\n" );
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
