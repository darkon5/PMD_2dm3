package moe.gimme.pizarra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Pizarra extends AppCompatActivity {

    private void limpiar(){
        Board.mCanvas.drawColor(0xFF000000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizarra);
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
                Toast.makeText(getApplicationContext(),"¡Huele a pizarra nueva!",Toast.LENGTH_SHORT).show();
                limpiar();
                //showJugador();
                return true;
            case R.id.m_about:
                return true;
            case R.id.m_howto:
                return true;
            default:
                return false;
        }
    }
}
