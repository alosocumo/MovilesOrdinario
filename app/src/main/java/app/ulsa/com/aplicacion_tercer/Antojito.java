
package app.ulsa.com.aplicacion_tercer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Antojito extends Fragment {
    ListView listAntojitos;

    public Antojito() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.getActivity().setTitle("Antojito");
        View view = inflater.inflate(R.layout.fragment_bebida, container, false);
        listAntojitos = (ListView) view.findViewById(R.id.list);
        SincronizarAntojito sincronizarAn = new SincronizarAntojito(this);
        sincronizarAn.execute();
        return view;
    }

    public ListView getListAntojitos() {
        return listAntojitos;
    }

    public void setListAntojitos(ListView listAntojitos) {
        this.listAntojitos = listAntojitos;
    }
}

