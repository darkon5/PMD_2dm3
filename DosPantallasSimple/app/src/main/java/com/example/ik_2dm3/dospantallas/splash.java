package com.example.ik_2dm3.dospantallas;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class splash extends AppCompatActivity {

    private Button cambiaActivity;
    private EditText txtSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cambiaActivity = (Button) findViewById(R.id.cambiaActivity);
        txtSplash = (EditText) findViewById(R.id.txtSplash);



        if (getIntent().getStringExtra("id")!=null){
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            String paso = extras.getString("id");
                txtSplash.setText(paso);
            }
    }

    public void jump2princi (View view) {
        //Toast.makeText(getBaseContext(),"Toasty dice: " + cajatexto.getText(),Toast.LENGTH_SHORT).show();
        //Toast.makeText(getBaseContext(),"buenoo",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), princi.class);
            i.putExtra("id",txtSplash.getText().toString());
        //Toast.makeText(getBaseContext(),"Toasty dice: " + txtSplash.getText(),Toast.LENGTH_SHORT).show();
        startActivity(i);

    }
}
