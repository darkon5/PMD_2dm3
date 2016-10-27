package moe.gimme.eljueguitodivertido;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class flipMain extends AppCompatActivity {

    private SeekBar seekX , seekY , seekTramas ;
    private RadioButton radioColor , radioNumero ;
    private CheckBox checkSonido , checkVibracion ;
    private TextView textX , textY , textTramas ;
    private int valorX,valorY,valorTramas;
    private Button btnJugar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        seekX = (SeekBar) findViewById(R.id.barX);
        seekY = (SeekBar) findViewById(R.id.barY);
        seekTramas = (SeekBar) findViewById(R.id.barZ);
        textX = (TextView) findViewById(R.id.textX);
        textY = (TextView) findViewById(R.id.textY);
        textTramas = (TextView) findViewById(R.id.textZ);
        btnJugar = (Button) findViewById(R.id.button);
        radioColor = (RadioButton) findViewById(R.id.radioColores);

        radioNumero = (RadioButton) findViewById(R.id.radioNumeros);
        checkVibracion = (CheckBox)  findViewById(R.id.checkVibracion);
        checkSonido= (CheckBox)  findViewById(R.id.checkSonido);


        updateX(seekX.getProgress());
        updateY(seekY.getProgress());
        updateTramas(seekTramas.getProgress());

        textX.setText(getString(R.string.textoX) + ": " + 3);
        textY.setText(getString(R.string.textoY) + ": " + 3);
        textTramas.setText(getString(R.string.textoZ) + ": " + 2);



        seekX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onStartTrackingTouch (SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){}
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                updateX(seekBar.getProgress());
            }
        });
        seekY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onStartTrackingTouch (SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){}
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                updateY(seekBar.getProgress());
            }
        });
        seekTramas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onStartTrackingTouch (SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){}
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromuser){
                updateTramas(seekBar.getProgress());
            }
        });

        btnJugar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                jugar();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_gameconfig,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.m_Jugador:
                showPlayer();
                return true;
            case R.id.m_howto:
                showHowto();
                return true;
            case R.id.m_about:
                showAbout();
                return true;
            default:
                return false;
        }
    }

    public void showPlayer(){
        Intent i = new Intent(this, Jugador.class);
        startActivity(i);
    }
    public void showHowto(){
        Intent i = new Intent(this, howto.class);
        startActivity(i);
    }
    public void showAbout(){
        Intent i = new Intent(this, about.class);
        startActivity(i);
    }


    protected void updateX(int progress){
        int x = progress + 3;

        textX.setText(getString(R.string.textoX) + ": " + x);
    }
    protected void updateY(int progress){
        int y = progress + 3;
        textY.setText(getString(R.string.textoY) + ": " + y);
    }
    protected void updateTramas(int progress){
        int z =progress + 2;
        textTramas.setText(getString(R.string.textoZ) + ": " + z);

    }


    public void jugar(){
        boolean vibra=false;
        Intent intent = new Intent(this, GameField.class);

        intent.putExtra("sbx",seekX.getProgress()+3);
        intent.putExtra("sby",seekY.getProgress()+3);
        intent.putExtra("sbTramas",seekTramas.getProgress()+2);
        if(radioColor.isChecked()){
            intent.putExtra("tipoJuego","colores");
        }
        if(radioNumero.isChecked()){
            intent.putExtra("tipoJuego","numeros");
        }
        if(checkVibracion.isChecked()){
            vibra=true;
        }
        intent.putExtra("ckVibracion",vibra);
        if(checkSonido.isChecked()){
            intent.putExtra("ckSonido",true);
        }
        startActivityForResult(intent,1);

    }




}
