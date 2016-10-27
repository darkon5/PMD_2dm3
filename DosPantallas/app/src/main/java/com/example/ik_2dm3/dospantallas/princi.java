package com.example.ik_2dm3.dospantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class princi extends AppCompatActivity {

    private Button volver;
    private EditText txtPrinci;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princi);
        volver = (Button) findViewById(R.id.volver);
        txtPrinci = (EditText) findViewById(R.id.txtPrinci);
        textView2 = (TextView) findViewById(R.id.textView2);

        //textView2.setText(getIntent().getStringExtra("TextoInterdimensional"));
            //txtPrinci.setText(getIntent().getStringExtra("TextoInterdimensional"));
        //Intent intent = getIntent();
        //Bundle extras = intent.getExtras();
        //String paso = extras.getString("id");
        txtPrinci.setText(getIntent().getExtras().getString("id"));
        //Toast.makeText(getBaseContext(),"Toasty dice: " + paso,Toast.LENGTH_SHORT).show();

    }

    public void irSplash(View view){
        //Toast.makeText(getBaseContext(),"im tryna",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), splash.class);

        i.putExtra("id",txtPrinci.getText().toString());
        startActivity(i);
    }
}
