
package app.ulsa.com.aplicacion_tercer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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


public class Sincronizar extends AsyncTask<Void,String,String> {

    private Comida comida;
    private String idCategoria;


    public Sincronizar(Comida comida) {
        this.comida = comida;


    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.
        StringBuilder result = new StringBuilder();
        try {

            JSONObject contenedorUC = new JSONObject();

            String url = "http://172.16.15.102:8080/Comida/categoria/obtenerAlimentos?id=" + 18;
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


          /* DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(contenedorUC.toString());
            dos.flush();
            dos.close();*/

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
                final Adaptador ad = new Adaptador(comida.getActivity(), R.layout.fila, lisAlimento);
                ListView lisV =  comida.getListComidas();
                lisV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Alimento alimento = ad.getItem(i);
                        Intent intent = new Intent(comida.getActivity(), DetalleActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("alimento", alimento);
                        intent.putExtras(b);
                        comida.getActivity().startActivity(intent);
                    }
                });
                lisV.setAdapter(ad);
                ad.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void onCancelled() {

    }
}
