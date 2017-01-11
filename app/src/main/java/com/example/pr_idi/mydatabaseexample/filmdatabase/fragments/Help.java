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

public class Help extends Fragment {

    public static final String TAG = "Help";
    private OnFragmentInteractionListener mListener;

    public Help() {
        // Required empty public constructor
    }

    public static Help newInstance() {
        Help fragment = new Help();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        TextView texto1 = (TextView) view.findViewById(R.id.textView4);
        TextView texto2 = (TextView) view.findViewById(R.id.textView9);
        TextView texto3 = (TextView) view.findViewById(R.id.textView10);
        TextView texto4 = (TextView) view.findViewById(R.id.textView11);
        TextView texto5 = (TextView) view.findViewById(R.id.textView12);
        TextView texto6 = (TextView) view.findViewById(R.id.textView13);
        TextView texto7 = (TextView) view.findViewById(R.id.textView14);
        TextView texto8 = (TextView) view.findViewById(R.id.textView15);
        TextView texto9 = (TextView) view.findViewById(R.id.textView16);
        TextView texto10 = (TextView) view.findViewById(R.id.textView17);
        TextView texto11 = (TextView) view.findViewById(R.id.textView18);
        TextView texto12 = (TextView) view.findViewById(R.id.textView19);
        TextView texto13 = (TextView) view.findViewById(R.id.textView20);
        texto1.setText("Vista principal y menú\n");

        texto2.setText("El menú es desplegable fent clic sobre l'icona de menú o arrossegant el dit de forma horitzontal " +
                "des de la bora esquerra de la pantalla cap al centre.\n\n" +
                "Disposem de les següents opcions:\n");

        texto3.setText("Cercar per títol\n" +
                "Buscar por actors / actrius\n" +
                "Listat de pel·lícules\n" +
                "Afegir pel·lícula\n\n");

        texto4.setText("Cercar per títol\n");

        texto5.setText("Aquesta és la vista principal a més d'una opció al menú\n\n" +
                "Ens mostrarà una llista de pel·lícules per pantalla. \n\n" +
                "Les pel·lícules, estaran ordenades per nom en ordre alfabètic. \n\n" +
                "Just al costat de cada nom ens apareixerà una paperera com a icona d'esborrat. Fer clic sobre el botó d'esborrar llançarà un missatge de confirmació de l'acció. En cas afirmatiu la pel·lícula serà esborrada de forma definitiva de l'aplicació.\n\n" +
                "Fer clic sobre el títol d'una pel·lícula ens mourà a una vista on podrem veure els detalls de la informació de la pel·lícula seleccionada.\n\n");
        texto6.setText("Buscar per actors / actrius\n");

        texto7.setText("Ens mostrarà una llista d'actors protagonistes de les pel·lícules emmagatzemades a l'aplicació.\n\n" +
                "Els noms dels actors estaran ordenats en ordre alfabètic.\n\n" +
                "Fer clic sobre un dels actors ens mostrarà una llista detallada de les pel·lícules que ha protagonitzat.\n\n");

        texto8.setText("Llista de pel·lícules\n");

        texto9.setText("Ens mostrarà una llista detallada de totes les pel·lícules disponibles.\n" +
                "Mostrant\n");

        texto10.setText("Títol\n" +
                "Any de estrena\n" +
                "País\n" +
                "Director/a\n" +
                "Protagonista\n" +
                "Notes de la crítica\n\n");

        texto11.setText("Afegir pel·lícula\n");

        texto12.setText("Ens mostrarà un formulari on introduir les dades de la nova pel·lícula a introduir a l'aplicació.\n");

        texto13.setText("Títol: Camp de text\n" +
                "Any d'estrena: Menú desplegable des de 1985 fins a l'actualitat\n" +
                "País: Menú desplegable ordenat alfabèticament.\n" +
                "Director/a: Camp de text.\n" +
                "Protagonista: Camp de text.\n" +
                "Nota de la crítica: Hi haurà una capsa amb la nota assignada i una barra tàctil per modificar-la.\n");
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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
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
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
