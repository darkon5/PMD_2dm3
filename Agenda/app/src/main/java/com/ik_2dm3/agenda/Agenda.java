package com.ik_2dm3.agenda;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

public class Agenda extends AppCompatActivity {

    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        lista = (ListView) findViewById(R.id.ListaPersonas);

        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().
                        execute(
                                new URL("http://sentora.fptxurdinaga.net/~asier/pmdm_local/agenda.php")

                        );

            } else {
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void hola(URL url) {
    }

    class JsonTask extends AsyncTask<URL, Void, List<Persona>> {
        @Override
        protected List<Persona> doInBackground(URL... urls) {
            List<Persona> personas = null;
            try {
                //establecer la conexion
                con = (HttpURLConnection) urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                int statusCode = con.getResponseCode();

                if (statusCode != 200) {
                    personas = new ArrayList<>();
                    personas.add(new Persona("", "", "", ""));

                } else {
                    JSONObject obj = new JSONObject(" .... ");
                    String pageName = obj.getJSONObject("pageInfo").getString("pageName");

                    JSONArray arr = obj.getJSONArray("posts");
                    for (int i = 0; i < arr.length(); i++)
                    {
                        String post_id = arr.getJSONObject(i).getString("nombre");
                    }
                    //Parsear el flujo con formato JSON
                    /*InputStream in = new BufferedInputStream(con.getInputStream());
                    JsonPersonParser parser = new JsonPersonParser();
                    personas = parser.leerFlujoJson(in);*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
            return personas;


        }

        @Override
        protected void onPostExecute(List<Persona> personas) {
            //aqui asignar los objetos JSON asignados para que los meta en un array
            if (personas != null) {
                adaptador = new AdaptadorDePersonas(getBaseContext(), personas);
                lista.setAdapter(adaptador);
            } else {
                Toast.makeText(getBaseContext(),
                        "Error del adaptador ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



