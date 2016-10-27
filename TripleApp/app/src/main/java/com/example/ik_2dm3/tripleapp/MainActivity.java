package com.example.ik_2dm3.tripleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private EditText cajatexto;
    private Button boton;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajatexto = (EditText) findViewById(R.id.cajatexto);
        boton = (Button) findViewById(R.id.boton);
        //boton.setOnClickListener(botonToast());
        resultado = (TextView) findViewById(R.id.resultado);



    }



    public void botonToast(View view) {
        resultado.setText(cajatexto.getText());
        Toast.makeText(getBaseContext(),"Toasty dice: " + cajatexto.getText(),Toast.LENGTH_SHORT).show();

    }


}
