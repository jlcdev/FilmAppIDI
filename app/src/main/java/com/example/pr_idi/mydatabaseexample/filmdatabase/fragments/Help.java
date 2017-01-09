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

        texto2.setText("El menú es desplegable pulsando sobre el icono de menú o arrastrando el dedo de forma horizontal " +
                        "desde el borde izquierdo de la pantalla hacia el centro.\n\n" +
                        "Disponemos de las siguientes opciones:\n");

        texto3.setText("Buscar por título\n" +
                        "Buscar por actores / actrices\n" +
                        "Listado de películas\n" +
                        "Añadir película\n\n");

        texto4.setText("Buscar por título\n");

        texto5.setText("Esta es la vista principal además de una opción en el menú\n\n" +
                        "Nos mostrará una lista de películas por pantalla. \n\n" +
                        "Las películas, estarán ordenadas por nombre en orden alfabético. \n\n" +
                        "Junto a cada una de las papeleras nos aparecerá un icono de borrado. Pulsar sobre el botón de borrado lanzará un mensaje de confirmación de la acción. En caso afirmativo la película será borrada de forma definitiva de la aplicación.\n\n" +
                        "Pulsar sobre el título de una película nos moverá a una vista donde podremos ver los detalles (lista de películas) de la información de la película seleccionada.\n\n");
        texto6.setText("Buscar por actores / actrices\n");

        texto7.setText("Nos mostrará una lista de actores protagonistas de las películas almacenadas en la aplicación.\n\n" +
                        "Los nombres de los actores estarán ordenados en orden alfabético.\n\n" +
                        "Pulsar sobre uno de los actores nos mostrará una lista detallada (lista de películas) de las películas que ha protagonizado.\n\n");

        texto8.setText("Lista de películas\n");

        texto9.setText("Nos mostrará una lista detallada de todas las películas disponibles.\n" +
                        "Mostrando\n");

        texto10.setText("Título\n" +
                        "Año de estreno\n" +
                        "País\n" +
                        "Director/a\n" +
                        "Protagonista\n" +
                        "Notas de la crítica\n\n");

        texto11.setText("Añadir película\n");

        texto12.setText("Nos mostrará un formulario donde introducir los datos de la nueva película a introducir en la aplicación.\n");

        texto13.setText("Título: Campo de texto\n" +
                        "Año de estreno: Menú desplegable desde 1985 hasta la actualidad\n" +
                        "País: Menú desplegable ordenado alfabéticamente.\n" +
                        "Director/a: Campo de texto\n" +
                        "Protagonista: Campo de texto\n" +
                        "Nota de la crítica: Habrá una caja con la nota asignada y unos botones a izquierda y derecha para modificarla.\n");
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
