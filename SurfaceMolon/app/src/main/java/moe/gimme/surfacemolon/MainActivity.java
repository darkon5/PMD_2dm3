package moe.gimme.surfacemolon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static moe.gimme.surfacemolon.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    private vista v = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(activity_main);
        v = new vista(this);
        setContentView(v);



    }
}
