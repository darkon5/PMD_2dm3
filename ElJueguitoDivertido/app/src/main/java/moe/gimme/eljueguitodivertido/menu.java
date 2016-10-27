package moe.gimme.eljueguitodivertido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class menu extends AppCompatActivity {

    private SeekBar barX, barY, barZ;
    private TextView textX, textY, textZ;
    private int valorX,valorY,valorZ;

    private Button juegar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        barX  = (SeekBar) findViewById(R.id.barX);
        barY  = (SeekBar) findViewById(R.id.barY);
        barZ  = (SeekBar) findViewById(R.id.barZ);

        textX = (TextView) findViewById(R.id.textX);
        textY = (TextView) findViewById(R.id.textY);
        textZ = (TextView) findViewById(R.id.textZ);

        juegar = (Button)findViewById(R.id.button);

        updateX(barX.getProgress());
        updateY(barY.getProgress());
        updateZ(barZ.getProgress());

        textX.setText(getString(R.string.textoX) + ": " + valorX);
        textY.setText(getString(R.string.textoY) + ": " + valorY);
        textZ.setText(getString(R.string.textoZ) + ": " + valorZ);

        barX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStartTrackingTouch (SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){}
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                updateX(barX.getProgress());
            }
        });
        barY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStartTrackingTouch (SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){}
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                updateY(barY.getProgress());
            }
        });
        barZ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStartTrackingTouch (SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){}
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                updateZ(barZ.getProgress());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_gameconfig,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.m_Jugador:
                Toast.makeText(getApplicationContext(),"Le diste al menu nomas",Toast.LENGTH_SHORT).show();
                //showJugador();
                return true;
            case R.id.m_about:
                showAbout();
                return true;
            case R.id.m_howto:
                showHowto();
                return true;
            default:
                return false;
        }
    }

    private void updateX(int progress){
        valorX = progress+3;
        textX.setText(getString(R.string.textoX) + ": " + valorX);
    }
    private void updateY(int progress){
        valorY = progress+3;
        textY.setText(getString(R.string.textoY) + ": " + valorY);
    }
    private void updateZ(int progress){
        valorZ = progress+2;
        textZ.setText(getString(R.string.textoZ) + ": " + valorZ);
    }
    public void showJugador(){

        Intent i = new Intent(getApplicationContext(), Jugador.class);
        startActivity(i);
    }
    public void showAbout(){
        Intent i = new Intent(getApplicationContext(), about.class);
        startActivity(i);
    }
    public void showHowto(){
        Intent i = new Intent(this, howto.class);
        startActivity(i);
    }
    public void juegar(View view){
        Toast.makeText(getApplicationContext(), "Valores: " + valorX + " " + valorY + " " + valorZ + ".", Toast.LENGTH_SHORT).show();
    }
}
