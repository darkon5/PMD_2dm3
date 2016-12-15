package com.ik_2dm3.agenda;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.ik_2dm3.agenda.R.id.list_item;
import static com.ik_2dm3.agenda.R.id.listaPersonas;


public class MainActivity extends AppCompatActivity {

    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(listaPersonas);

        try{

            ConnectivityManager conntMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conntMgr.getActiveNetworkInfo();
            if(networkInfo!=null && networkInfo.isConnected()){
                //pido por red de datos -> JSON
                new JsonTask().execute(
                    new URL("http://sentora.fptxurdinaga.net/~asier/pmdm_local/agenda.php")
                );
            }else{
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }

        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }

    //Hilo independiente
    public class JsonTask extends AsyncTask<URL, Void, List<Persona>>{//1.Parametro de entrada, 2.Progreso ,3.Nos devuelve List<Persona>

        @Override
        protected List<Persona> doInBackground(URL... urls){
            List<Persona> personas = null;
            try{
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);
                int statusCode = con.getResponseCode();

                if(statusCode!=200){
                    personas = new ArrayList<>();
                    personas.add(new Persona("nadie","nadie","nadie", "nadie"));

                }else{
                    //Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());
                    JsonPersonParser parser = new JsonPersonParser();
                    personas = parser.leerFlujoJson(in);
                    Toast.makeText(MainActivity.this, "" + personas, Toast.LENGTH_LONG).show();;
                }

            }catch(Exception e){
                e.printStackTrace();
            }finally{
                con.disconnect();
            }

            return personas;
        }
        @Override
        protected void onPostExecute (List<Persona> personas){
            //aqui asignar los objetos JSON asignados para que los meta en un array
            if(personas!=null){
                adaptador = new AdaptadorDePersonas(getBaseContext(), personas);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(getBaseContext(), "Error del adaptador ", Toast.LENGTH_SHORT).show();
            }
        }


    }


    public void llamar(View vista){
        //do stuff/*
        Intent callIntent = new Intent(Intent.ACTION_DIAL);

        //TextView TXTnumero = (TextView) lista.findViewById(R.id.telefono);//+"945384306";
        //MainActivity.this.lista.findViewById(R.id.telefono[i]).getTelefono();

        //String numero = TXTnumero.getText().toString();

        /*callIntent.setData(Uri.parse("tel:"+numero));
        try {
            startActivity(callIntent);
        }catch(Exception e){
            e.printStackTrace();
        }//*/

        Toast.makeText(getBaseContext(),"numero: " ,Toast.LENGTH_SHORT).show();
    }
    public void editar(View vista){
        //do stuff/*

        Toast.makeText(getBaseContext(),"buenoo" + " loco",Toast.LENGTH_SHORT).show();
    }
}



