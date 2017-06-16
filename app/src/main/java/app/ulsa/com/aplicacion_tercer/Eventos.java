
package app.ulsa.com.aplicacion_tercer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Eventos extends Fragment {
    ListView listEventos;

    public Eventos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.getActivity().setTitle("Eventos");
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);
        listEventos = (ListView) view.findViewById(R.id.list);
        SincronizarEvento sincronizarEvento = new SincronizarEvento(this);
        sincronizarEvento.execute();
        return view;
    }

    public ListView getListEventos() {
        return listEventos;
    }

    public void setListEventos(ListView listEventos) {
        this.listEventos = listEventos;
    }
}
