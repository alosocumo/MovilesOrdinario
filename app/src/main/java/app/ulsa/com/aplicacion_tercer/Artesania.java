
package app.ulsa.com.aplicacion_tercer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Artesania extends Fragment {
    ListView listArtesanias;

    public Artesania() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.getActivity().setTitle("Artesanías");
        View view = inflater.inflate(R.layout.fragment_artesania, container, false);
        listArtesanias = (ListView) view.findViewById(R.id.list);
        SincronizarArtesania sincronizarAr = new SincronizarArtesania(this);
        sincronizarAr.execute();
        return view;
    }

    public ListView getListArtesanias() {
        return listArtesanias;
    }

    public void setListArtesanias(ListView listArtesanias) {
        this.listArtesanias = listArtesanias;
    }
}
