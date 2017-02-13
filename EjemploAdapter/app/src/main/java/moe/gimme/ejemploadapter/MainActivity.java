package moe.gimme.ejemploadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mensajes;
    ListAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        // creamos nuestra coleccion de datos
        mensajes = new ArrayList();
        mensajes.add("uno");
        mensajes.add("dos");
        mensajes.add("tres");
        mensajes.add("cuatro");
        mensajes.add("cinco");

        // creamos el listado
        listAdapter = new ListAdapter(this, mensajes);

        // establecemos el adaptador en la lista
        listView.setAdapter(listAdapter);
    }
}
