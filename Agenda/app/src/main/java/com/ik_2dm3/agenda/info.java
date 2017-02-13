package com.ik_2dm3.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.ik_2dm3.agenda.R.layout.activity_info;

/**
 * Created by ik_2dm3 on 15/12/2016.
 */


public class info extends AppCompatActivity {

    private Button volver;
    private TextView txtTlf;
    private TextView txtNombre;
    static final Integer LOCATION = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(activity_info);
        //volver = (Button) findViewById(R.id.volver);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtTlf = (TextView) findViewById(R.id.txtTlf);

        //textView2.setText(getIntent().getStringExtra("TextoInterdimensional"));
        //txtPrinci.setText(getIntent().getStringExtra("TextoInterdimensional"));
        //Intent intent = getIntent();
        //Bundle extras = intent.getExtras();
        //String paso = extras.getString("id");
        txtNombre.setText(getIntent().getExtras().getString("idNombre"));
        txtTlf.setText(getIntent().getExtras().getString("idTlf"));

        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);





        //Toast.makeText(getBaseContext(),"Toasty dice: " + paso,Toast.LENGTH_SHORT).show();

    }

    private void askForPermission(String permission, Integer requestCode) {

        Toast.makeText(this, "I tried", Toast.LENGTH_SHORT).show();


        if (ContextCompat.checkSelfPermission(info.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(info.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(info.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(info.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }




    public void llamar(View vista){
        //do stuff/*
        //int permissionCheck = ContextCompat.checkSelfPermission(this.getBaseContext(), Manifest.permission.CALL_PHONE);

        Intent callIntent = new Intent(Intent.ACTION_DIAL);

        //TextView TXTnumero = (TextView) lista.findViewById(R.id.telefono);//+"945384306";
        //MainActivity.this.lista.findViewById(R.id.telefono[i]).getTelefono();

        //String numero = TXTnumero.getText().toString();

        callIntent.setData(Uri.parse("tel:"+txtTlf.getText().toString()));
        try {
            startActivity(callIntent);
        }catch(Exception e){
            e.printStackTrace();
        }

        //Toast.makeText(getBaseContext(),"numero: " ,Toast.LENGTH_SHORT).show();
    }


    public void irSplash(View view){
        //Toast.makeText(getBaseContext(),"im tryna",Toast.LENGTH_SHORT).show();
        //Intent i = new Intent(getApplicationContext(), splash.class);

        //i.putExtra("id",txtPrinci.getText().toString());
        //startActivity(i);
    }
}
