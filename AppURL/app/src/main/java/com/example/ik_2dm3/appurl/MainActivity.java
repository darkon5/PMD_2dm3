package com.example.ik_2dm3.appurl;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrir;
    private EditText txtURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtURL = (EditText)findViewById(R.id.txtURL);
        btnAbrir = (Button)findViewById(R.id.btnAbrir);


    }

    public void abrirURL(View view){

        String url = txtURL.getText().toString();
        if (!url.startsWith("https://") && !url.startsWith("http://")){
            url = "http://" + url;
        }
            Uri uri = Uri.parse(url); // missing 'http://' will cause crashed

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
