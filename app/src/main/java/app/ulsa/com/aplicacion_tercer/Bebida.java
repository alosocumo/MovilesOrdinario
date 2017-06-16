
package app.ulsa.com.aplicacion_tercer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Bebida extends Fragment {
    ListView listBebidas;

    public Bebida() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.getActivity().setTitle("Bebidas");
        View view = inflater.inflate(R.layout.fragment_bebida, container, false);
        listBebidas = (ListView) view.findViewById(R.id.list);
        SincronizarBebida sincronizarB = new SincronizarBebida(this);
        sincronizarB.execute();
        return view;
    }

    public ListView getListBebidas() {
        return listBebidas;
    }

    public void setListBebidas(ListView listBebidas) {
        this.listBebidas = listBebidas;
    }
}

