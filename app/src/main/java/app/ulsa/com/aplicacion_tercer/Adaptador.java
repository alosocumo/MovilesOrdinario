package app.ulsa.com.aplicacion_tercer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Alondra on 06/09/2016.
 */
public class Adaptador extends ArrayAdapter<Alimento> {
    private Activity context;
    private List<Alimento> listaAlimentos;

    public Adaptador(Activity context, int resource, List<Alimento> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listaAlimentos=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View fila = convertView;
        if (fila == null){
            LayoutInflater inflater = context.getLayoutInflater();
            fila = inflater.inflate(R.layout.fila, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.nombre = (TextView) fila.findViewById(R.id.nombresito);
            viewHolder.imagen = (ImageView) fila.findViewById(R.id.imgUrl);


            fila.setTag(viewHolder);
        }


        ViewHolder holder = (ViewHolder) fila.getTag();
        Alimento alimento = this.listaAlimentos.get(position);
        holder.nombre.setText(alimento.getNombre());

        Picasso.with(context).load(alimento.getImgUrl()).resize(100,100).centerCrop().into(holder.imagen);
        return fila;
    }


    static class ViewHolder {
        public TextView nombre;
        public ImageView imagen;

    }
}
