package app.ulsa.com.aplicacion_tercer;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.transition.Explode;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SincronizarM s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //  mMap.addMarker()

        Explode explode = new Explode();
        explode.setDuration(700); // Duración en milisegundos
        getWindow().setExitTransition(explode);

        s = new SincronizarM(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng edificioG = new LatLng(17.021988, -96.7219343);
        mMap.addMarker(new MarkerOptions().position(edificioG).title("La Salle"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(edificioG));
        CameraPosition zoom = new CameraPosition.Builder().target(edificioG).zoom(13).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(zoom));

        s.execute();

    }
    public void agregarMarcador(String nombre, double latitud, double longitud){
        LatLng marcadores = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(marcadores).title(nombre));


    }


}

class SincronizarM extends AsyncTask<Void,String,String> {

    MapsActivity mapsActivity;

    public SincronizarM(MapsActivity activity) {
        this.mapsActivity = activity;

    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.
        StringBuilder result = new StringBuilder();
        try {

            JSONObject contenedorUC = new JSONObject();

            String url = "http://10.179.228.28:8080/Comida/alimento/obtenerAlimentos";
            //String url = "http://172.16.13.156:8080/Comida/alimento/obtenerAlimentos";
            URL direccion = new URL(url);
            HttpURLConnection con = (HttpURLConnection) direccion.openConnection();
            con.setConnectTimeout(5000);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "plain/text");
            con.setRequestProperty("Content-Length", contenedorUC.toString().length() + "");
            con.setDoInput(true);
            con.setDoOutput(false);
            con.setReadTimeout(5000);

            /*
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(contenedorUC.toString());
            dos.flush();
            dos.close();
*/
            int codigo = con.getResponseCode();
            //con esto se puede leer la respuesta de mi conexion
            if (codigo == HttpURLConnection.HTTP_OK) {
                InputStream recibirC = new BufferedInputStream(con.getInputStream());
                BufferedReader leerResultado = new BufferedReader(new InputStreamReader(recibirC));
                String linea;
                while ((linea = leerResultado.readLine()) != null) {
                    result.append(linea);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // TODO: register the new account here.
        return result.toString();
    }

    @Override
    protected void onPostExecute(final String result) {

        if (!result.equals("")) {
            Log.e("Hola", result);
            try {

                JSONArray alimentosRecibidos = new JSONArray(result);
                List<Alimento> lisAlimento = new ArrayList<>();
                for (int i = 0; i < alimentosRecibidos.length(); i++) {
                    JSONObject alimentoDelJSONArray = alimentosRecibidos.getJSONObject(i);
                    Alimento alimento = new Alimento();
                    alimento.setNombre(alimentoDelJSONArray.getString("nombre"));
                    alimento.setQue(alimentoDelJSONArray.getString("que"));
                    alimento.setDonde(alimentoDelJSONArray.getString("donde"));
                    alimento.setLatitud(alimentoDelJSONArray.getString("latitud"));
                    alimento.setLongitud(alimentoDelJSONArray.getString("longitud"));
                    alimento.setImgUrl(alimentoDelJSONArray.getString("url"));

                    lisAlimento.add(alimento);
                }

                for(Alimento alimento: lisAlimento) {
                    this.mapsActivity.agregarMarcador(alimento.getNombre() + " - " + alimento.donde,
                            Double.parseDouble(alimento.latitud), Double.parseDouble(alimento.longitud));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void onCancelled() {

    }
}