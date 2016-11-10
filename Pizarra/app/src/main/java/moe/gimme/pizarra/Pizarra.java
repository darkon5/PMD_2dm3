package moe.gimme.pizarra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Pizarra extends AppCompatActivity {

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
                Toast.makeText(getApplicationContext(),"Le diste al menu nomas",Toast.LENGTH_SHORT).show();
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
