package app.ulsa.com.aplicacion_tercer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Comida extends Fragment {

    ListView listComidas;

    public Comida() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.getActivity().setTitle("Comidas");
        View view = inflater.inflate(R.layout.fragment_comida, container, false);
        listComidas = (ListView) view.findViewById(R.id.list);

        Sincronizar sincronizarC = new Sincronizar(this);
        sincronizarC.execute();

        return view;
    }

    public ListView getListComidas() {
        return listComidas;
    }

    public void setListComidas(ListView listComidas) {
        this.listComidas = listComidas;
    }
}

