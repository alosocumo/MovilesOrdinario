package app.ulsa.com.aplicacion_tercer;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alondra on 27/03/2017.
 */
public class AcercaAdaptador extends RecyclerView.Adapter<AcercaAdaptador.InicioViewHolder> {

    private static final int LENGTH = 18;

    private final String[] mPlaces;
    private final Drawable[] mPlaceAvators;

    public AcercaAdaptador(Context context) {
        Resources resources = context.getResources();
        mPlaces = resources.getStringArray(R.array.objetos);
        TypedArray a = resources.obtainTypedArray(R.array.objeto_avator);
        mPlaceAvators = new Drawable[a.length()];
        for (int i = 0; i < mPlaceAvators.length; i++) {
            mPlaceAvators[i] = a.getDrawable(i);
        }
        a.recycle();
    }

    @Override
    public InicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new InicioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InicioViewHolder holder, int position) {
        holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
        holder.name.setText(mPlaces[position % mPlaces.length]);
    }

    @Override
    public int getItemCount() {
        return mPlaces.length;
    }


    public static class InicioViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;

        public InicioViewHolder(View itemView) {
            super(itemView);
        }


    }

}

