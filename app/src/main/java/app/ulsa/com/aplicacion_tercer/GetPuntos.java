package app.ulsa.com.aplicacion_tercer;

import android.os.AsyncTask;

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

/**
 * Created by Alondra on 08/03/2017.
 */
public class GetPuntos extends AsyncTask<Void, String, String> {

    private List<Punto> lista;

    public GetPuntos(){
        this.lista = new ArrayList<>();
        this.onPostExecute(this.doInBackground());
    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.
        StringBuilder result = new StringBuilder();
        try {
            JSONObject contenedorUC = new JSONObject();
            String url = "http://172.16.13.22:8080/Comida/categoria/obtenerAlimentos?id=" + 27;
            URL direccion = new URL(url);
            HttpURLConnection con = (HttpURLConnection) direccion.openConnection();
            con.setConnectTimeout(5000);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "plain/text");
            con.setRequestProperty("Content-Length", contenedorUC.toString().length() + "");
            con.setDoInput(true);
            con.setDoOutput(false);
            con.setReadTimeout(5000);
            int codigo = con.getResponseCode();
            if (codigo == HttpURLConnection.HTTP_OK) {
                InputStream recibirB = new BufferedInputStream(con.getInputStream());
                BufferedReader leerResultado = new BufferedReader(new InputStreamReader(recibirB));
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
            try {
                JSONArray lugares = new JSONArray(result);
                for (int i = 0; i < lugares.length(); i++) {
                    JSONObject alimentoDelJSONArray = lugares.getJSONObject(i);
                    Punto p = new Punto();
                    p.setLatitud(alimentoDelJSONArray.getDouble("latitud"));
                    p.setLongitud(alimentoDelJSONArray.getDouble("longitud"));
                    p.setTitulo(alimentoDelJSONArray.getString("titulo"));
                    p.setDescripcion(alimentoDelJSONArray.getString("descripcion"));
                    lista.add(p);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Punto> getLista(){
        return this.lista;
    }
}



